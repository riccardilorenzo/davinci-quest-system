package model.attribute;

import java.util.Map;

/**
 * Interface which defines the structure of an Attribute, used primarily by the Commander class as an HashSet.
 * Each Attribute has a value (an integer greater than zero) and a name.
 * @author TheMind
 *
 */
public interface Attribute {
	
	/**
	 * Factory method for instancing new Attribute objects.
	 * @param name The name of the Attribute, case insensitive.
	 * @param value The value of the specified Attribute.
	 * @return A new object representing the Attribute specified.
	 * @throws AttributeNotValidException
	 */
	public static Attribute of(String name, int value) throws AttributeNotValidException {
		Map<String, Attribute> attributes = Map.of(
				"Agility", new Agility(value),
				"Charisma", new Charisma(value),
				"Intelligence", new Intelligence(value));
		if (attributes.containsKey(name)) return attributes.get(name);
		else throw new AttributeNotValidException("Impossibile ottenere l'attributo specificato.");
	}
	
	/**
	 * Getter method for the value of the attribute.
	 * @return The value of the attribute, given as an integer value greater than zero.
	 */
	int getValue();
	
	/**
	 * Getter method for the name of the attribute.
	 * @return A String representing which Attribute the object is.
	 */
	String getName();

	/**
	 * Compares two Attributes and checks if this Attribute is upper or equal to other Attribute.
	 * @param other The other Attribute to be compared. Must be of the same type as the caller.
	 * @return True if this Attribute has a major or equal value to the other Attribute, false otherwise.
	 */
	boolean satisfies(Attribute other);
}
