package model;

/**
 * This enum provides the possible attribute values for a Commander (or a Mission requirement).
 * The values in this enumerator <b>are ordered</b> by name. The implementator should consider this.
 * @author TheMind
 */
public enum Attribute {
    AGILITY("Agilità"), CHARISMA("Carisma"), CONTACTS("Contatti"),
    FAME("Fama"), INTELLIGENCE("Intelligenza");

    private final String name;

    private Attribute(String name) { this.name = name; }

    @Override
    public String toString() { return this.name; }
}