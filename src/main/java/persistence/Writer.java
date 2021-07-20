package persistence;

import model.Commander;
import model.mission.Ending;
import model.mission.Mission;
import model.mission.MissionStatus;
import model.mission.Step;

public interface Writer {

    /**
     * Updates a Commander at the specified destination.
     * @param commander The Commander to update.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void updateCommander(Commander commander) throws WriterException;

    /**
     * Creates a Mission at the specified destination.
     * @param mission The Mission to create.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void createMission(Mission mission) throws WriterException;

    /**
     * Updates a Mission at the specified destination.
     * @param mission The Mission to update.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void updateMission(Mission mission) throws WriterException;

    /**
     * Updates a MissionStatus at the specified destination, relative to the given Commander and Mission.
     * @param cmdr The Commander whose MissionStatus belongs to.
     * @param mission The Mission whose MissionStatus belongs to.
     * @param status The new MissionStatus.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void updateMissionStatus(Commander cmdr, Mission mission, MissionStatus status)
            throws WriterException;

    /**
     * Updates a MissionStatus at the specified destination, relative to the given Commander and Mission, with a certain starting index.
     * @param cmdr The Commander whose MissionStatus belongs to.
     * @param mission The Mission whose MissionStatus belongs to.
     * @param status The new MissionStatus.
     * @param index The Step index to start with.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void updateMissionStatus(Commander cmdr, Mission mission, MissionStatus status, int index)
            throws WriterException;

    /**
     * Updates a Step index at the specified destination, relative to the given Commander and Mission.
     * @param cmdr The Commander whose Step belongs to.
     * @param mission The Mission whose Step belongs to.
     * @param step The new Step index.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void updateMissionStepIndex(Commander cmdr, Mission mission, int step) throws WriterException;

    /**
     * Updates a Step at the specified destination, relative to the given Commander and Mission.
     * @param cmdr The Commander whose Step belongs to.
     * @param mission The Mission whose Step belongs to.
     * @param step The new Step.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void updateMissionStep(Commander cmdr, Mission mission, Step step) throws WriterException;

    /**
     * Creates an Ending at the specified destination.
     * @param ending The Ending to create.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void createEnding(Ending ending) throws WriterException;

    /**
     * Deletes an Ending at the specified destination.
     * @param ending The Ending to delete.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void deleteEnding(Ending ending) throws WriterException;

    /**
     * Creates a Step at the specified destination for the given Mission.
     * @param mission The Mission the new Step belongs to.
     * @param step The Step to create.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void createStep(Mission mission, Step step) throws WriterException;

    /**
     * Updates an Ending at the specified destination.
     * @param ending The Ending to update.
     * @throws WriterException In case of errors in I/O, or bad data format.
     */
    void updateEnding(Ending ending) throws WriterException;

    /**
     * Closes every stream the object has opened.
     */
    void close();
}
