package persistence;

import model.Commander;
import model.mission.Ending;
import model.mission.Mission;
import model.mission.MissionStatus;
import model.mission.Step;

import java.util.Set;

public interface Reader {

    /**
     * Reads a Commander from the provided source.
     * @param source The data source, given as String.
     * @param id The Commander ID, unique.
     * @return The Commander read.
     */
    Commander readCommander(String source, String id) throws BadDataFormatException;

    /**
     * Reads a Mission from the provided source.
     * @param source The data source, given as String.
     * @param id The Mission ID, unique.
     * @return The Mission read.
     */
    Mission readMission(String source, int id) throws BadDataFormatException;

    /**
     * Reads a MissionStatus related to the given Mission and Commander, from the specified source.
     * @param source The data source, given as String.
     * @param cmdr The Commander whose MissionStatus belongs to.
     * @param mission The Mission whose MissionStatus belongs to.
     * @return The MissionStatus read.
     */
    MissionStatus readMissionStatus(String source, Commander cmdr, Mission mission) throws BadDataFormatException;

    /**
     * Reads a Step related to the given Mission and Commander, from the specified source.
     * @param source The data source, given as String.
     * @param cmdr The Commander whose Step belongs to.
     * @param mission The Mission whose Step belongs to.
     * @return The Step read.
     */
    Step readStep(String source, Commander cmdr, Mission mission) throws BadDataFormatException;

    /**
     * Reads all the Endings from the provided source.
     * @param source The data source, given as String.
     * @return The Set containing every possible ending.
     */
    Set<Ending> readEndings(String source) throws BadDataFormatException;

    /**
     * Reads a specified Ending from the provided source.
     * @param source The data source, given as String.
     * @param id The ID of the Ending to be read.
     * @return The Ending read.
     */
    Ending readEndings(String source, int id) throws BadDataFormatException;

    /**
     * Closes every stream the object has opened.
     */
    void close();
}
