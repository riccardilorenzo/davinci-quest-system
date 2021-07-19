package model.mission;

import model.QuestException;
import model.mission.requirement.Requirement;

import java.util.List;
import java.util.Objects;

public class Step implements Comparable<Step> {
    private final int index, idMission;
    private String desc;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Requirement> requirements;

    public Step(int index, int idMission, String desc, List<Requirement> requirements) {
        if (index < 0) throw new QuestException("L'indice dello Step deve essere maggiore o uguale a 0.");
        if (desc == null || requirements == null) throw new IllegalArgumentException("Description null, aborting.");
        if (requirements.size() == 0) throw new QuestException("Lo step specificato deve avere almeno un requisito per il completamento.");

        this.index = index;
        this.desc = desc;
        this.requirements = requirements;
        this.idMission = idMission;
    }

    /**
     * Getter method for the Step index.
     * @return The Step index.
     */
    public int getIndex() {
        return index;
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

    @Override
    public int compareTo(Step o) {
        return this.index - o.index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return getIndex() == step.getIndex() && getIdMission() == step.getIdMission();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getIdMission());
    }
}
