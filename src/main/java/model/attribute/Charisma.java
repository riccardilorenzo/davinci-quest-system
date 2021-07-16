package model.attribute;

/**
 * This class implements the interface Attribute, and defines the Charisma attribute for a Commander.
 * @author TheMind
 *
 */
public class Charisma implements Attribute, Comparable<Charisma> {
	private int value;
	
	public Charisma(int value) {
		if (value < 0) throw new AttributeNotValidException("Il carisma non può essere negativo.");
		this.value = value;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public String getName() {
		return "Carisma";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Charisma in) {
			return in.getValue() == this.getValue();
		}
		return false;
	}

	@Override
	public int compareTo(Charisma o) {
		return this.getValue() - o.getValue();
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
