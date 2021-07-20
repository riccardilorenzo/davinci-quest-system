package model.mission;

import model.Attribute;

import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * This class represents an ending for one or more quests, each with a (eventually empty or maybe negative) reward.
 * @author TheMind
 */
public class Ending {
    private final int id;
    private String text;
    private SortedMap<Attribute, Integer> rewards;

    /**
     * Main constructor of the Ending class.
     * @param id The unique ID of this ending.
     * @param text The GDR text of this ending.
     * @param rewards The rewards (extra attribute points) of this ending.
     */
    public Ending(int id, String text, SortedMap<Attribute, Integer> rewards) {
        if (text == null || rewards == null)
            throw new IllegalArgumentException("Parameters null in Ending constructor, aborting.");

        this.id = id;
        this.text = text.trim();
        this.rewards = rewards;
    }

    /**
     * Getter method of the ID.
     * @return The ID of the Ending.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter method of the attribute rewards.
     * @return The attribute rewards, given as a Map with Attribute as keys and relative extra points as ints.
     */
    public SortedMap<Attribute, Integer> getRewards() {
        return this.rewards;
    }

    /**
     * Removes an Attribute reward from the rewards.
     * @param attribute The Attribute to be removed.
     * @return The attribute rewards, with the specified Attribute eventually removed.
     */
    public SortedMap<Attribute, Integer> removeReward(Attribute attribute) {
        if (attribute == null) throw new IllegalArgumentException("Attribute null, aborting.");
        this.rewards.remove(attribute); return this.rewards;
    }

    /**
     * Clears every reward of this Ending.
     * @return A new, empty, SortedMap.
     */
    public SortedMap<Attribute, Integer> clearRewards() {
        this.rewards = new TreeMap<>();
        return this.rewards;
    }

    /**
     * Adds an Attribute reward to the rewards.
     * @param attribute The Attribute to be added.
     * @return The attribute rewards, with the specified Attribute eventually added.
     */
    public SortedMap<Attribute, Integer> addReward(Attribute attribute, int value) {
        if (attribute == null) throw new IllegalArgumentException("Attribute null, aborting.");
        this.rewards.put(attribute, value); return this.rewards;
    }

    /**
     * Getter method of the GDR text.
     * @return The GDR text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Setter method of the GDR text.
     * @param text The String that replaces the old GDR text.
     */
    public void setText(String text) {
        if (text == null) throw new IllegalArgumentException("Text null, aborting.");
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ending ending = (Ending) o;
        return getId() == ending.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
