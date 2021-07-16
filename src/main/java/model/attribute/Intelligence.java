package model.attribute;

/**
 * This class implements the interface Attribute, and defines the Intelligence attribute for a Commander.
 * @author TheMind
 *
 */
public class Intelligence implements Attribute, Comparable<Intelligence> {
	private int value;
	
	public Intelligence(int value) {
		if (value < 0) throw new AttributeNotValidException("L'intelligenza non può essere negativa.");
		this.value = value;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public String getName() {
		return "Intelligenza";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Intelligence in) {
			return in.getValue() == this.getValue();
		}
		return false;
	}

	@Override
	public int compareTo(Intelligence o) {
		return this.getValue() - o.getValue();
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
