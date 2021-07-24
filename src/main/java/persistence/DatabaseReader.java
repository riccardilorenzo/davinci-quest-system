package persistence;

import model.Attribute;
import model.Commander;
import model.QuestException;
import model.mission.Ending;
import model.mission.Mission;
import model.mission.MissionStatus;
import model.mission.Step;

import java.sql.*;
import java.util.*;

public class DatabaseReader implements Reader {
    private final Connection conn;

    // TODO: optimizations with joins on ending (and maybe mission) queries

    /**
     * Main constructor for DatabaseReader class.
     * @param jdbcConnector JDBC Connector string.
     */
    public DatabaseReader(String jdbcConnector) {
        if (jdbcConnector == null) throw new IllegalArgumentException("JDBC Connector string null, aborting.");
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(jdbcConnector);
        } catch (ClassNotFoundException | SQLException e) {
            throw new QuestException(e);
        }
    }

    /**
     * @see Reader#readCommander(String)
     */
    @Override
    public Commander readCommander(String id) throws BadDataFormatException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM commanders WHERE login = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            int rowCount = rs.last() ? rs.getRow() : 0;
            if (rowCount == 1) {
                String name = rs.getString("name"), realName = rs.getString("realname");
                int points = rs.getInt("points");
                boolean isAdmin = rs.getBoolean("admin");
                ps.close();

                return new Commander(id, name, (realName == null ? name : realName), readAttributes(id), points, isAdmin);
            } else throw new BadDataFormatException("Utente non registrato. Richiedi il tuo ID da Lisa, sul server Discord della DaVinci Corporation!");
        } catch (SQLException e) {
            throw new BadDataFormatException(e);
        }
    }

    private SortedMap<Attribute, Integer> readAttributes(String id) throws SQLException {
        SortedMap<Attribute, Integer> res = new TreeMap<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM cmdr_attributes WHERE login = ?;");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            try {
                res.put(Attribute.valueOf(rs.getString("attribute")), rs.getInt("value"));
            } catch (IllegalArgumentException e) {
                throw new QuestException(e);
            }
        }
        ps.close();
        return res;
    }

    /**
     * @see Reader#readMission(int)
     */
    @Override
    public Mission readMission(int id) throws BadDataFormatException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM missions WHERE mission_id = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int rowCount = rs.last() ? rs.getRow() : 0;

            if (rowCount == 1) {
                Mission res = new Mission(id, rs.getString("name"), readSteps(id), rs.getInt("needed_points"),
                        rs.getInt("reward_points"), readRequirements(id),
                        rs.getTimestamp("issuedatetime").toLocalDateTime(), readEndings(id));
                ps.close();
                return res;
            } else throw new BadDataFormatException("Non esiste una missione relativa alla ricerca eseguita (ID: " + id + ").");
        } catch (SQLException e) {
            throw new BadDataFormatException(e);
        }
    }

    private SortedMap<Attribute, Integer> readRequirements(int missionId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM mission_requirements WHERE mission_id = ?;");
        return getAttributeIntegerSortedMap(missionId, ps);
    }

    /**
     * @see Reader#readMissionsOf(Commander)
     */
    @Override
    public SortedSet<Mission> readMissionsOf(Commander cmdr) throws BadDataFormatException {
        try {
            SortedSet<Mission> res = new TreeSet<>();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cmdr_missions WHERE cmdr_id = ?;");
            ps.setString(1, cmdr.getLogin());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                res.add(readMission(rs.getInt("mission_id")));
            }
            ps.close();

            return res;
        } catch (SQLException e) {
            throw new BadDataFormatException(e);
        }
    }

    /**
     * @see Reader#readMissionStatus(Commander, Mission)
     */
    @Override
    public MissionStatus readMissionStatus(Commander cmdr, Mission mission) throws BadDataFormatException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cmdr_missions WHERE cmdr_id = ? AND mission_id = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, cmdr.getLogin()); ps.setInt(2, mission.getId());
            ResultSet rs = ps.executeQuery();
            int rowCount = rs.last() ? rs.getRow() : 0;

            if (rowCount == 1) {
                return MissionStatus.valueOf(rs.getString("status"));
            } else if (rowCount == 0) return MissionStatus.NOT_ACCEPTED;
            else throw new BadDataFormatException("Errore critico: due o più stati trovati per la missione e il comandante selezionati.");
        } catch (SQLException | IllegalArgumentException e) {
            throw new BadDataFormatException(e);
        }
    }

    /*/**
     * @see Reader#readStep(Commander, Mission)
     */
    /*@Override
    public Step readStep(Commander cmdr, Mission mission) throws BadDataFormatException {
        return null;
    }*/

    @Override
    public List<Step> readSteps(int missionId) throws BadDataFormatException {
        return null;
    }

    /**
     * @see Reader#readLastStepIndex(Commander, Mission)
     */
    @Override
    public int readLastStepIndex(Commander cmdr, Mission mission) throws BadDataFormatException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cmdr_missions WHERE cmdr_id = ? AND mission_id = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, cmdr.getLogin()); ps.setInt(2, mission.getId());
            ResultSet rs = ps.executeQuery();
            int rowCount = rs.last() ? rs.getRow() : 0;

            if (rowCount == 1) {
                return rs.getInt("value");
            } else if (rowCount == 0) return -1;
            else throw new BadDataFormatException("Errore critico: due o più indici trovati per la missione e il comandante selezionati.");
        } catch (SQLException e) {
            throw new BadDataFormatException(e);
        }
    }

    /**
     * @see Reader#readEndings()
     */
    @Override
    public SortedSet<Ending> readEndings() throws BadDataFormatException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM endings;");
            ResultSet rs = ps.executeQuery();

            SortedSet<Ending> res = new TreeSet<>();
            while (rs.next()) {
                res.add(new Ending(rs.getInt("id"), rs.getString("text"), readRewards(rs.getInt("id"))));
            }
            ps.close();
            return res;
        } catch (SQLException e) {
            throw new BadDataFormatException(e);
        }
    }

    private SortedMap<Attribute, Integer> readRewards(int endingId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM ending_rewards WHERE ending_id = ?;");
        return getAttributeIntegerSortedMap(endingId, ps);
    }

    private SortedMap<Attribute, Integer> getAttributeIntegerSortedMap(int id, PreparedStatement ps) throws SQLException {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        SortedMap<Attribute, Integer> res = new TreeMap<>();
        while (rs.next()) {
            try {
                res.put(Attribute.valueOf(rs.getString("attribute")), rs.getInt("value"));
            } catch (IllegalArgumentException e) {
                throw new SQLException(e);
            }
        }
        ps.close();
        return res;
    }

    /**
     * @see Reader#readEndings(Mission)
     */
    @Override
    public SortedSet<Ending> readEndings(Mission m) throws BadDataFormatException {
        return this.readEndings(m.getId());
    }

    /**
     * @see Reader#readEndings(int)
     */
    @Override
    public SortedSet<Ending> readEndings(int missionId) throws BadDataFormatException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mission_endings WHERE mission_id = ?;");
            ps.setInt(1, missionId);
            ResultSet rs = ps.executeQuery();

            SortedSet<Ending> res = new TreeSet<>();
            while (rs.next()) {
                res.add(readEnding(rs.getInt("ending_id")));
            }
            ps.close();
            return res;
        } catch (SQLException e) {
            throw new BadDataFormatException(e);
        }
    }

    /**
     * @see Reader#readEnding(int)
     */
    @Override
    public Ending readEnding(int id) throws BadDataFormatException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM endings WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Ending res = new Ending(id, rs.getString("text"), readRewards(id));
                ps.close();
                return res;
            } else throw new BadDataFormatException("Non esiste un finale con l'ID specificato.");
        } catch (SQLException e) {
            throw new BadDataFormatException(e);
        }
    }

    /**
     * @see Reader#close()
     */
    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new QuestException(e);
        }
    }
}
