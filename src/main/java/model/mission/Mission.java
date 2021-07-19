package model.mission;

import model.QuestException;
import model.mission.requirement.Requirement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Mission {
    private int id, neededPoints, rewardPoints;
    private String name, desc;
    private Map<String, Integer> requirements;
    private LocalDateTime issueDateTime;
    private List<Requirement> requirementList;

    /**
     * Main constructor for Mission.
     * @param id The Mission ID.
     * @param name The Mission name.
     * @param desc The Mission GDR description.
     * @param neededPoints The Mission needed points.
     * @param requirements The Mission needed requirements, in terms of a name associated with its value.
     * @param issueDateTime The Mission creation date and time.
     * @param requirementList The List of the requirements for this Mission to be completed.
     */
    public Mission(int id, String name, String desc, int neededPoints, int rewardPoints, Map<String, Integer> requirements,
                   LocalDateTime issueDateTime, List<Requirement> requirementList) {
        if (name == null || desc == null || requirements == null || issueDateTime == null || requirementList == null)
            throw new QuestException("Null arguments for Mission constructor, aborting.");
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.neededPoints = neededPoints;
        this.requirements = requirements;
        this.issueDateTime = issueDateTime;
        this.requirementList = requirementList;
    }

    /**
     * Getter method for the ID.
     * @return The Mission ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method for the name.
     * @return The Mission name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the description.
     * @return The Mission description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter method for the Attribute requirements.
     * @return The Mission Attribute requirements.
     */
    public Map<String, Integer> getAttributeRequirements() {
        return requirements;
    }

    /**
     * Getter method for the needed points.
     * @return The Mission needed points.
     */
    public int getNeededPoints() {
        return neededPoints;
    }

    /**
     * Getter method for the issue date and time.
     * @return The Mission issue date and time.
     */
    public LocalDateTime getIssueDateTime() {
        return issueDateTime;
    }

    /**
     * Getter method for the List of fulfillment requirements.
     * @return The List containing every requirement for the Mission.
     */
    public List<Requirement> getFulfillmentRequirements() {
        return this.requirementList;
    }

    /**
     * Getter method for the reward points amount.
     * @return The amount of reward points.
     */
    public int getRewardPoints() {
        return this.rewardPoints;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).withLocale(Locale.getDefault());
        return "Missione del " + dtf.format(this.getIssueDateTime()) + " - " + this.getName() + " - " + System.lineSeparator()
                + "Punti necessari: " + this.getNeededPoints() + " (ricompensa: " + this.getRewardPoints() + ")" + System.lineSeparator()
                + "Requisiti (attributi):" + System.lineSeparator() + formatRequirements() + System.lineSeparator()
                + "Requisiti (completamento):" + System.lineSeparator() + formatFinalRequirements() + System.lineSeparator()
                + System.lineSeparator() + this.getDesc();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return getId() == mission.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    private String formatRequirements() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());

        for (String attr : this.getAttributeRequirements().keySet())
            sj.add("\t- " + attr + ": " + this.getAttributeRequirements().get(attr));

        return sj.toString();
    }

    private String formatFinalRequirements() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());

        for (Requirement req : this.getFulfillmentRequirements())
            sj.add("\t- " + req.toString());

        return sj.toString();
    }

    // TODO: missing completion requirements (code them here or use a separate class Requirement). Write the method
}
