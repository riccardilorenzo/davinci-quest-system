package model.mission;

public enum MissionStatus {
    SUCCESS("Completata"), FAILURE("Fallita"),
    PENDING("In svolgimento"), NOT_ACCEPTED("Da accettare");

    private String desc;

    MissionStatus(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Missione " + this.desc.toLowerCase();
    }

    public String getDesc() { return this.desc; }
}
