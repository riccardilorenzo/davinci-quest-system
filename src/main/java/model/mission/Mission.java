package model.mission;

public interface Mission {
    /**
     * Getter method for the mission ID.
     * @return The mission ID, unique.
     */
    int getId();

    /**
     * Getter method for the name of the mission.
     * @return The name of the mission.
     */
    String getName();

    /**
     * Getter method for the points needed to start this mission.
     * @return The points needed to start this mission.
     */
    int getPoints();

    /**
     * Getter method for the description text of the mission.
     * @return The description text of the mission.
     */
    String getDescription();
}
