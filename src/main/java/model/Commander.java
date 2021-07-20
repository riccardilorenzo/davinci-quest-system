package model;

import java.util.Map;
import java.util.StringJoiner;

// TODO: rewrite the commander-related tables in the DB to match this structure

/**
 * This class is used to instantiate a Commander, which is used as a basic user model.
 * @author TheMind
 */
public class Commander {
	private final String login, commanderName;
	private String realName;
	private Map<Attribute, Integer> attributes;
	private int points;
	private final boolean isAdmin;
	
	/**
	 * Primary constructor for Commander class.
	 * @param login Login ID used for logging and referring to the commander.
	 * @param commanderName Commander name as used in game.
	 * @param realName "Real" GDR name, used for roleplay purposes.
	 * @param attributes HashSet of Attribute, which stores the attributes of the commander.
	 * @param points Points the Commander has.
	 * @param isAdmin Whether this Commander has admin privileges or not.
	 */
	public Commander(String login, String commanderName, String realName,
			Map<Attribute, Integer> attributes, int points, boolean isAdmin) {
		if (login == null || commanderName == null || attributes == null || realName == null)
			throw new IllegalArgumentException("Argomenti null, impossibile proseguire.");
		
		this.login = login;
		this.commanderName = commanderName;
		this.attributes = attributes;
		this.points = points;
		this.realName = realName;
		this.isAdmin = isAdmin;
	}
	
	/**
	 * Secondary constructor for Commander class, without the real name.
	 * @param login Login ID used for logging and referring to the commander.
	 * @param commanderName Commander name as used in game.
	 * @param attributes HashSet of Attribute, which stores the attributes of the commander.
	 * @param points Points the Commander has.
	 * @param isAdmin Whether this Commander has admin privileges or not.
	 */
	public Commander(String login, String commanderName, Map<Attribute, Integer> attributes,
			int points, boolean isAdmin) {
		this(login, commanderName, commanderName, attributes, points, isAdmin);
	}
	
	/**
	 * Secondary constructor for Commander class, without the real name.
	 * @param login Login ID used for logging and referring to the commander.
	 * @param commanderName Commander name as used in game.
	 * @param attributes HashSet of Attribute, which stores the attributes of the commander.
	 * @param points Points the Commander has.
	 */
	public Commander(String login, String commanderName, Map<Attribute, Integer> attributes,
			int points) {
		this(login, commanderName, commanderName, attributes, points, false);
	}

	/**
	 * Getter method for the login ID.
	 * @return The Login ID of the Commander.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Getter method for the commander name.
	 * @return The name of the Commander.
	 */
	public String getCommanderName() {
		return commanderName;
	}

	/**
	 * Getter method for the real name.
	 * @return The GDR name of the Commander (the Commander name if the real name is null).
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * Setter method for the real name.
	 * @param realName A string containing the GDR name.
	 */
	public void setRealName(String realName) {
		this.realName = changeEmpty(realName);
	}

	private String changeEmpty(String str) {
		if (str == null) throw new IllegalArgumentException("Real name null, aborting.");
		if (str.trim().isBlank()) return this.getCommanderName();
		return str.trim();
	}

	/**
	 * Getter method for the attributes.
	 * @return A Map containing all the attributes of the Commander.
	 */
	public Map<Attribute, Integer> getAttributes() {
		return attributes;
	}

	/**
	 * Changes or adds a new attribute with its value.
	 * @param attribute The attribute to be added or edited.
	 * @param value The value of the attribute.
	 */
	public void setAttribute(Attribute attribute, int value) {
		if (attribute == null) throw new IllegalArgumentException("Attribute null, aborting.");
		if (value < 0) this.attributes.put(attribute, 0);
		this.attributes.put(attribute, value);
	}

	/**
	 * Getter method for the points.
	 * @return The points the Commander currently has.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Setter method for the points.
	 * @param points The points of the Commander, given as int. Cannot be negative.
	 */
	public void setPoints(int points) {
		if (points < 0) throw new QuestException("Non puoi impostare ad un valore negativo i punti di CMDR " + this.getCommanderName());
		this.points = points;
	}
	
	/**
	 * Utility method for adjusting the points of the Commander.
	 * @param points Points to be added or subtracted. Cannot result in the Commander having a negative amount of points.
	 */
	public void addPoints(int points) {
		if (this.getPoints() + points < 0) throw new QuestException("Non puoi togliere più punti a CMDR " + this.getCommanderName() + " di quanti ne possieda attualmente.");
		this.setPoints(this.getPoints() + points);
	}

	/**
	 * Auxiliary method to check if this Commander has admin privileges.
	 * @return True if this Commander has admin privileges, false otherwise.
	 */
	public boolean isAdmin() {
		return this.isAdmin;
	}

	@Override
	public String toString() {
		return "CMDR " + this.getCommanderName() + " (alias " + this.getRealName() + ")" + System.lineSeparator()
				+ "Punteggio: " + this.getPoints() + " DaVinci Quest Points" + System.lineSeparator()
				+ "Attributi:" + System.lineSeparator() + "\t-" + printAttributes();
	}

	private String printAttributes() {
		StringJoiner sj = new StringJoiner(System.lineSeparator() + "\t-");

		for (Attribute attr : this.getAttributes().keySet())
			sj.add(attr + ": " + this.getAttributes().get(attr));

		return sj.toString();
	}
}
