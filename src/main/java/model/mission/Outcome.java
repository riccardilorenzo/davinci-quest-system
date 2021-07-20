package model.mission;

public record Outcome(boolean success, String outcome) {

    @Override
    public String toString() {
        return this.outcome;
    }
}
