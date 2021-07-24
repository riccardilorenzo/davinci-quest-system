package model.mission;

import model.QuestException;
import model.mission.requirement.Requirement;

import java.util.List;

public class Step {
    private final int idMission;
    private String desc;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Requirement> requirements;
    // TODO: implement Step requirements in the DB (maybe rethink them as only one requirement for Step --> See other TODOs)

    public Step(int idMission, String desc, List<Requirement> requirements) {
        if (desc == null || requirements == null) throw new IllegalArgumentException("Description null, aborting.");
        if (requirements.size() == 0) throw new QuestException("Lo step specificato deve avere almeno un requisito per il completamento.");

        this.desc = desc;
        this.requirements = requirements;
        this.idMission = idMission;
    }

    /**
     * Getter method for the GDR description.
     * @return The GDR description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter method for the mission ID.
     * @return The Mission ID this Step is relative to.
     */
    public int getMissionId() {
        return this.idMission;
    }

    /**
     * Setter method for the GDR description.
     * @param desc The GDR description to update to.
     */
    public void setDesc(String desc) {
        if (desc == null) throw new IllegalArgumentException("Description null, aborting.");
        this.desc = desc.trim();
    }

    /**
     * The getter method of the requirements.
     * @return A List containing every Requirement.
     */
    public List<Requirement> getRequirements() {
        return requirements;
    }

    /**
     * Auxiliary method to add a Requirement for this Step.
     * @param req The new Requirement.
     * @return This Step (supports pattern cascading).
     */
    public Step addRequirement(Requirement req) {
        this.requirements.add(req); return this;
    }

    /**
     * Auxiliary method to remove a Requirement for this Step.
     * @param req The Requirement to be removed.
     * @return This Step (supports pattern cascading).
     */
    public Step removeRequirement(Requirement req) {
        // TODO: missing equals() in Requirement (implementing classes)
        this.requirements.remove(req); return this;
    }
}
