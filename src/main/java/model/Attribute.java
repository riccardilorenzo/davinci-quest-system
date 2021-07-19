package model;

/**
 * This enum provides the possible attribute values for a Commander (or a Mission requirement).
 * The values in this enumerator <b>are ordered</b> by name. The implementator should consider this.
 * @author TheMind
 */
public enum Attribute {
    AGILITY("Agilità"), CHARISMA("Carisma"), CONTACTS("Conoscenze"),
    FAME("Fama"), INTELLIGENCE("Intelligenza");

    private String name;

    private Attribute(String name) { this.name = name; }
}