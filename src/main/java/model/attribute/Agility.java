package model.attribute;

/**
 * This class implements the interface Attribute, and defines the Agility attribute for a Commander.
 * @author TheMind
 *
 */
public class Agility implements Attribute, Comparable<Agility> {
	private int value;
	
	public Agility(int value) {
		if (value < 0) throw new AttributeNotValidException("L'agilità non può essere negativa.");
		this.value = value;
	}
	
	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public String getName() {
		return "Agilità";
	}

	/**
	 * @see Attribute#satisfies(Attribute)
	 */
	@Override
	public boolean satisfies(Attribute other) {
		if (other instanceof Agility ag) {
			return this.getValue() >= ag.getValue();
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Agility in) {
			return in.getValue() == this.getValue();
		}
		return false;
	}

	@Override
	public int compareTo(Agility o) {
		return this.getValue() - o.getValue();
	}
	
	@Override
	public String toString() {
		return this.getName() + ": " + this.getValue();
	}
}
