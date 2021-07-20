package model.mission;

public enum MissionStatus {
    SUCCESS("Completata"), FAILED("Fallita"),
    PENDING("In svolgimento"), NOT_ACCEPTED("Da accettare");

    private String desc;

    MissionStatus(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
