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
		if (attributes.containsKey(name.toLowerCase())) return attributes.get(name);
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
	 * 
	 * @param obj Another Attribute to compare with.
	 * @return True if both the objects are instances of the same class, and have the same value.
	 */
	boolean equals(Object obj);
	
	/**
	 * toString() overriding. Might coincide with getName() method.
	 * @return A String representing the Attribute the object is. Might coincide with getName().
	 */
	String toString();
}
