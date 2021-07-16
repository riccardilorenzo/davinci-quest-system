package model.mission;

public enum MissionStatus {
    SUCCESS("Completata"), FAILURE("Fallita"),
    PENDING("In svolgimento"), NOT_ACCEPTED("Da completare");

    private String desc;

    MissionStatus(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
