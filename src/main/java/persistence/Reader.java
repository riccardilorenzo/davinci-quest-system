package persistence;

import model.Commander;
import model.mission.Ending;
import model.mission.Mission;
import model.mission.MissionStatus;
import model.mission.Step;

import java.util.List;
import java.util.SortedSet;

public interface Reader {

    /**
     * Reads a Commander from the provided source.
     * @param id The Commander ID, unique.
     * @return The Commander read.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    Commander readCommander(String id) throws BadDataFormatException;

    /**
     * Reads a Mission from the provided source.
     * @param id The Mission ID, unique.
     * @return The Mission read.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    Mission readMission(int id) throws BadDataFormatException;

    /**
     * Reads all the Missions accepted (and also completed/failed) from the provided source of the given Commander.
     * @param cmdr The Commander.
     * @return A Set containing all the Mission(s) of this Commander (NOT INCLUDING THOSE UNACCEPTED).
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    SortedSet<Mission> readMissionsOf(Commander cmdr) throws BadDataFormatException;

    /**
     * Reads a MissionStatus related to the given Mission and Commander, from the specified source.
     * @param cmdr The Commander whose MissionStatus belongs to.
     * @param mission The Mission whose MissionStatus belongs to.
     * @return The MissionStatus read. MissionStatus.NOT_ACCEPTED if not found.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    MissionStatus readMissionStatus(Commander cmdr, Mission mission) throws BadDataFormatException;

    /*/**
     * Reads a Step related to the given Mission and Commander, from the specified source.
     * @param cmdr The Commander whose Step belongs to.
     * @param mission The Mission whose Step belongs to.
     * @return The Step read.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    //Step readStep(Commander cmdr, Mission mission) throws BadDataFormatException; // Uses readLastStepIndex internally, then uses that index on cmdr

    /**
     * Reads the List of Step(s) related to the given Mission, from the specified source.
     * @param missionId The Mission these Step(s) belong to.
     * @return The Step(s) read, in a List.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    List<Step> readSteps(int missionId) throws BadDataFormatException;

    /**
     * Reads the last Step index related to the given Mission and Commander, from the specified source.
     * @param cmdr The Commander whose Step belongs to.
     * @param mission The Mission whose Step belongs to.
     * @return The last Step index as specified. -1 if not found.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    int readLastStepIndex(Commander cmdr, Mission mission) throws BadDataFormatException;

    /**
     * Reads all the Endings from the provided source.
     * @return The SortedSet containing every possible ending, not null.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    SortedSet<Ending> readEndings() throws BadDataFormatException;

    /**
     * Reads all the Endings from the provided source, relative to the given Mission.
     * @param m The Mission the Ending(s) belong to.
     * @return The SortedSet containing every ending relative to the mission, not null.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    SortedSet<Ending> readEndings(Mission m) throws BadDataFormatException;

    /**
     * Reads all the Endings from the provided source, relative to the given Mission.
     * @param missionId The Mission ID the Ending(s) belong to.
     * @return The SortedSet containing the ending with the given ID, not null.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    SortedSet<Ending> readEndings(int missionId) throws BadDataFormatException;

    /**
     * Reads a specified Ending from the provided source.
     * @param id The ID of the Ending to be read.
     * @return The Ending read.
     * @throws BadDataFormatException In case of errors in I/O, or bad data format.
     */
    Ending readEnding(int id) throws BadDataFormatException;

    /**
     * Closes every stream the object has opened.
     */
    void close();
}
