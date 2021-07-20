package model.mission;

import model.Attribute;
import model.QuestException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

// TODO: rewrite the mission part on the DB to support the Step, Ending and other new functionalities

/**
 * This class provides a Mission, made of one or multiple steps, and multiple endings.
 * @author TheMind
 */
public class Mission {
    private final int id;
    private int neededPoints, rewardPoints;
    private List<Step> steps;
    private String name;
    private SortedMap<Attribute, Integer> attributeRequirements;
    private final LocalDateTime issueDateTime;
    private Set<Ending> endings;

    /**
     * Main constructor for Mission.
     * @param id The Mission ID.
     * @param name The Mission name.
     * @param steps The Step(s) this mission has.
     * @param neededPoints The Mission needed points.
     * @param attributeRequirements The Mission needed requirements, in terms of a name associated with its value.
     * @param issueDateTime The Mission creation date and time.
     * @param endings A Set of final effects applied at the end of the Mission.
     */
    public Mission(int id, String name, List<Step> steps, int neededPoints, int rewardPoints,
                   SortedMap<Attribute, Integer> attributeRequirements, LocalDateTime issueDateTime, Set<Ending> endings) {
        if (name == null || steps == null || attributeRequirements == null || issueDateTime == null || endings == null)
            throw new IllegalArgumentException("Null arguments for Mission constructor, aborting.");
        if (steps.size() == 0) throw new QuestException("La missione specificata non può avere 0 step.");

        this.id = id;
        this.name = name;
        this.steps = steps;
        this.neededPoints = neededPoints;
        this.rewardPoints = rewardPoints;
        this.attributeRequirements = attributeRequirements;
        this.issueDateTime = issueDateTime;
        this.endings = endings;
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
     * Getter method for the List of Step(s) of this Mission.
     * @return The Mission list of Step(s).
     */
    public List<Step> getSteps() {
        return steps;
    }

    /**
     * Getter method for the Attribute requirements.
     * @return The Mission Attribute requirements.
     */
    public SortedMap<Attribute, Integer> getAttributeRequirements() {
        return attributeRequirements;
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
     * Getter method for the reward points amount.
     * @return The amount of reward points.
     */
    public int getRewardPoints() {
        return this.rewardPoints;
    }

    /**
     * Getter method for the endings.
     * @return The Set containing the endings of this Mission.
     */
    public Set<Ending> getEndings() {
        return this.endings;
    }

    /**
     * Adds an Ending to the Set of endings.
     * @param e The Ending to be added.
     * @return The Set of endings with the element eventually added.
     */
    public Set<Ending> addEnding(Ending e) {
        if (e == null) throw new IllegalArgumentException("Ending null, aborting.");
        this.endings.add(e); return this.endings;
    }

    /**
     * Removes an Ending from the Set of endings.
     * @param e The Ending to be removed.
     * @return The Set of endings with the element eventually removed.
     */
    public Set<Ending> removeEnding(Ending e) {
        if (e == null) throw new IllegalArgumentException("Ending null, aborting.");
        this.endings.remove(e); return this.endings;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).withLocale(Locale.getDefault());
        return "Missione del " + dtf.format(this.getIssueDateTime()) + " - " + this.getName() + " - " + System.lineSeparator()
                + "Numero di step: " + this.getSteps().size() + System.lineSeparator()
                + "Numero di finali: " + this.getEndings().size() + System.lineSeparator()
                + "Punti necessari: " + this.getNeededPoints() + " (ricompensa: " + this.getRewardPoints() + ")" + System.lineSeparator()
                + "Requisiti (attributi):" + System.lineSeparator() + formatRequirements();
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

        for (Attribute attr : this.getAttributeRequirements().keySet())
            sj.add("\t- " + attr + ": " + this.getAttributeRequirements().get(attr));

        return sj.toString();
    }

    /**
     * Setter method for the needed points.
     * @param neededPoints The new needed points. Must be not negative.
     */
    public void setNeededPoints(int neededPoints) {
        if (neededPoints < 0) throw new QuestException("I punti necessari per accettare la missione non possono essere negativi.");
        this.neededPoints = neededPoints;
    }

    /**
     * Setter method for the reward points.
     * @param rewardPoints The new reward points. Must be not negative.
     */
    public void setRewardPoints(int rewardPoints) {
        if (rewardPoints < 0) throw new QuestException("I punti di ricompensa non possono essere negativi.");
        this.rewardPoints = rewardPoints;
    }

    /**
     * Setter method for the Mission name.
     * @param name The new Mission name.
     */
    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("Mission name null, aborting.");
        this.name = name;
    }

    /**
     * Auxiliary method for adding new steps into the Mission.
     * @param step The Step to be added.
     * @return The list of Step(s) with the new Step added.
     */
    public List<Step> addStep(Step step) {
        this.steps.add(step);
        return this.steps;
    }

    /**
     * Auxiliary method for removing a step into the Mission.
     * @param step The Step to be removed.
     * @return The list of Step(s) with the specified Step removed.
     */
    public List<Step> removeStep(Step step) {
        this.steps.remove(step); return this.steps;
    }

    /**
     * Auxiliary method for removing a step into the Mission.
     * @param index The index of the Step to be removed..
     * @return The list of Step(s) with the specified Step removed.
     */
    public List<Step> removeStep(int index) {
        this.steps.remove(index); return this.steps;
    }

    /**
     * Adds or changes an attribute requirement for this Mission.
     * @param attribute The attribute to be added or edited.
     * @param value The new value of the specified attribute.
     * @return The Map of attributes, ordered by name.
     */
    public SortedMap<Attribute, Integer> addAttributeRequirement(Attribute attribute, int value) {
        if (attribute == null) throw new IllegalArgumentException("Attribute null, aborting.");
        if (value < 0) throw new QuestException("Il valore per l'attributo da inserire non può essere negativo.");
        this.attributeRequirements.put(attribute, value); return this.attributeRequirements;
    }

    /**
     * Removes an attribute from the requirements of this Mission.
     * @param attribute The attribute to be removed from the requirements.
     * @return The Map of attributes, ordered by name.
     */
    public SortedMap<Attribute, Integer> removeAttributeRequirement(Attribute attribute) {
        if (attribute == null) throw new IllegalArgumentException("Attribute null, aborting.");
        this.attributeRequirements.remove(attribute); return this.attributeRequirements;
    }
}
