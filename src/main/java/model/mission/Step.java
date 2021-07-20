package model.mission;

import model.QuestException;
import model.mission.requirement.Requirement;

import java.util.List;

public class Step {
    private final int idMission;
    private String desc;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Requirement> requirements;

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
    public int getIdMission() {
        return this.idMission;
    }

    /**
     * Setter method for the GDR description.
     * @param desc The GDR description to update to.
     */
    public void setDesc(String desc) {
        if (desc == null) throw new IllegalArgumentException("Description null, aborting.");
        this.desc = desc;
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
     * @return The List of Requirement(s) with the element eventually added.
     */
    public List<Requirement> addRequirement(Requirement req) {
        this.requirements.add(req); return this.requirements;
    }

    /**
     * Auxiliary method to remove a Requirement for this Step.
     * @param req The Requirement to be removed.
     * @return The List of Requirement(s) with the element eventually removed.
     */
    public List<Requirement> removeRequirement(Requirement req) {
        this.requirements.remove(req); return this.requirements;
    }
}
