package persistence;

import model.Commander;
import model.QuestException;
import model.mission.Ending;
import model.mission.Mission;
import model.mission.MissionStatus;
import model.mission.Step;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseWriter implements Writer {
    private final Connection conn;
    private ResultSet rs;

    // TODO: implement these methods

    /**
     * Main constructor for DatabaseWriter class.
     * @param jdbcConnector The JDBC Connector string.
     */
    public DatabaseWriter(String jdbcConnector) {
        if (jdbcConnector == null) throw new IllegalArgumentException("Connection string null, aborting.");
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(jdbcConnector);
        } catch (ClassNotFoundException | SQLException e) {
            throw new QuestException(e);
        }
    }

    /**
     * @see Writer#updateCommander(Commander)
     */
    @Override
    public void updateCommander(Commander commander) throws WriterException {

    }

    /**
     * @see Writer#createMission(Mission)
     */
    @Override
    public void createMission(Mission mission) throws WriterException {

    }

    /**
     * @see Writer#updateMission(Mission)
     */
    @Override
    public void updateMission(Mission mission) throws WriterException {

    }

    /**
     * @see Writer#updateMissionStatus(Commander, Mission, MissionStatus)
     */
    @Override
    public void updateMissionStatus(Commander cmdr, Mission mission, MissionStatus status) throws WriterException {

    }

    /**
     * @see Writer#updateMissionStatus(Commander, Mission, MissionStatus, int)
     */
    @Override
    public void updateMissionStatus(Commander cmdr, Mission mission, MissionStatus status, int index) throws WriterException {

    }

    /**
     * @see Writer#updateMissionStepIndex(Commander, Mission, int)
     */
    @Override
    public void updateMissionStepIndex(Commander cmdr, Mission mission, int step) throws WriterException {

    }

    /**
     * @see Writer#createEnding(Ending)
     */
    @Override
    public void createEnding(Ending ending) throws WriterException {

    }

    /**
     * @see Writer#deleteEnding(Ending)
     */
    @Override
    public void deleteEnding(Ending ending) throws WriterException {

    }

    /**
     * @see Writer#createStep(Mission, Step)
     */
    @Override
    public void createStep(Mission mission, Step step) throws WriterException {

    }

    /**
     * @see Writer#updateStep(Mission, Step)
     */
    @Override
    public void updateStep(Mission mission, Step step) throws WriterException {

    }

    /**
     * @see Writer#updateEnding(Ending)
     */
    @Override
    public void updateEnding(Ending ending) throws WriterException {

    }

    /**
     * @see Writer#close()
     */
    @Override
    public void close() {
        try {
            rs.close();
            conn.close();
        } catch (SQLException e) {
            throw new QuestException(e);
        }
    }
}
