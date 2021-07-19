package persistence;

import model.Commander;
import model.mission.Ending;
import model.mission.Mission;
import model.mission.MissionStatus;
import model.mission.Step;

public interface Writer {

    /**
     * Updates a Commander at the specified destination.
     * @param dest The data destination, given as String.
     * @param commander The Commander to update.
     */
    void updateCommander(String dest, Commander commander) throws BadDataFormatException;

    /**
     * Creates a Mission at the specified destination.
     * @param dest The data destination, given as String.
     * @param mission The Mission to create.
     */
    void createMission(String dest, Mission mission) throws BadDataFormatException;

    /**
     * Updates a Mission at the specified destination.
     * @param dest The data destination, given as String.
     * @param mission The Mission to update.
     */
    void updateMission(String dest, Mission mission) throws BadDataFormatException;

    /**
     * Updates a MissionStatus at the specified destination, relative to the given Commander and Mission.
     * @param dest The data destination, given as String.
     * @param cmdr The Commander whose MissionStatus belongs to.
     * @param mission The Mission whose MissionStatus belongs to.
     * @param status The new MissionStatus.
     */
    void updateMissionStatus(String dest, Commander cmdr, Mission mission, MissionStatus status)
            throws BadDataFormatException;

    /**
     * Updates a Step at the specified destination, relative to the given Commander and Mission.
     * @param dest The data destination, given as String.
     * @param cmdr The Commander whose Step belongs to.
     * @param mission The Mission whose Step belongs to.
     * @param step The new Step.
     */
    void updateMissionStep(String dest, Commander cmdr, Mission mission, Step step) throws BadDataFormatException;

    /**
     * Creates an Ending at the specified destination.
     * @param dest The data destination, given as String.
     * @param ending The Ending to create.
     */
    void createEnding(String dest, Ending ending) throws BadDataFormatException;

    /**
     * Updates an Ending at the specified destination.
     * @param dest The data destination, given as String.
     * @param ending The Ending to update.
     */
    void updateEnding(String dest, Ending ending) throws BadDataFormatException;

    /**
     * Closes every stream the object has opened.
     */
    void close();
}
