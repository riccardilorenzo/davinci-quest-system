package model;

import model.attribute.Attribute;
import model.mission.Mission;
import model.mission.MissionStatus;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * This class is used to instantiate a Commander, which is used as a basic user model.
 * @author TheMind
 *
 */
public class Commander {
	private String login, commanderName;
	private Optional<String> realName;
	private Set<Attribute> attributes;
	private int points;
	private boolean isAdmin;
	private Map<Mission, MissionStatus> missions;
	
	/**
	 * Primary constructor for Commander class.
	 * @param login Login ID used for logging and referring to the commander.
	 * @param commanderName Commander name as used in game.
	 * @param realName "Real" GDR name, used for roleplay purposes.
	 * @param attributes HashSet of Attribute, which stores the attributes of the commander.
	 * @param points Points the Commander has.
	 */
	public Commander(String login, String commanderName, String realName,
			Set<Attribute> attributes, int points, Map<Mission, MissionStatus> missions, boolean isAdmin) {
		if (login == null || commanderName == null || attributes == null || missions == null)
			throw new IllegalArgumentException("Argomenti null, impossibile proseguire.");
		
		this.login = login;
		this.commanderName = commanderName;
		this.attributes = attributes;
		this.points = points;
		this.realName = Optional.of(realName);
		this.isAdmin = isAdmin;
		this.missions = missions;
	}
	
	/**
	 * Secondary constructor for Commander class, without the real name.
	 * @param login Login ID used for logging and referring to the commander.
	 * @param commanderName Commander name as used in game.
	 * @param attributes HashSet of Attribute, which stores the attributes of the commander.
	 * @param points Points the Commander has.
	 * @param isAdmin Whether this Commander has admin privileges or not.
	 */
	public Commander(String login, String commanderName, Set<Attribute> attributes,
			int points, Map<Mission, MissionStatus> missions, boolean isAdmin) {
		this(login, commanderName, commanderName, attributes, points, missions, isAdmin);
	}
	
	/**
	 * Secondary constructor for Commander class, without the real name.
	 * @param login Login ID used for logging and referring to the commander.
	 * @param commanderName Commander name as used in game.
	 * @param attributes HashSet of Attribute, which stores the attributes of the commander.
	 * @param points Points the Commander has.
	 */
	public Commander(String login, String commanderName, Set<Attribute> attributes,
			int points, Map<Mission, MissionStatus> missions) {
		this(login, commanderName, commanderName, attributes, points, missions, false);
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
		return realName.orElse(this.getCommanderName());
	}

	/**
	 * Setter method for the real name.
	 * @param realName A string containing the GDR name.
	 */
	public void setRealName(String realName) {
		this.realName = Optional.ofNullable(changeEmpty(realName));
	}

	private String changeEmpty(String str) {
		if (str == null) return null;
		else {
			if (str.trim().isBlank()) return this.getCommanderName();
			else return str.trim();
		}
	}

	/**
	 * Getter method for the attributes.
	 * @return An HashSet containing all the Attribute(s) of the Commander.
	 */
	public Set<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * Setter method for the attributes.
	 * @param attributes An HashSet containing the attributes of the commander.
	 */
	public void setAttributes(Set<Attribute> attributes) {
		if (attributes == null) throw new IllegalArgumentException("Attributes for the Commander cannot be null.");
		this.attributes = attributes;
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
		if (points < 0) throw new IllegalArgumentException("Non puoi impostare ad un valore negativo i punti di CMDR " + this.getCommanderName());
		this.points = points;
	}
	
	/**
	 * Utility method for adjusting the points of the Commander.
	 * @param points Points to be added or subtracted. Cannot result in the Commander having a negative amount of points.
	 */
	public void addPoints(int points) {
		if (this.getPoints() + points < 0) throw new IllegalArgumentException("Non puoi togliere più punti a CMDR " + this.getCommanderName() + " di quanti ne possieda attualmente.");
		this.setPoints(this.getPoints() + points);
	}
	
	public boolean isAdmin() {
		return this.isAdmin;
	}
}
