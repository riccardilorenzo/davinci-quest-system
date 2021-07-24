package model.mission;

/**
 * This record class represents an ordered couple (boolean, String).
 * Used by MissionCommanderController when advancing missions.
 * @author TheMind
 */
public record Outcome(boolean success, String outcome) {

    @Override
    public String toString() {
        return this.outcome;
    }
}
