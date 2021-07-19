package model;

import model.mission.Mission;
import model.mission.MissionStatus;
import model.mission.requirement.Requirement;

import java.util.Map;
import java.util.Optional;

/**
 * This class is used to instantiate a Commander, which is used as a basic user model.
 * @author TheMind
 *
 */
public class Commander {
	private String login, commanderName;
	private Optional<String> realName;
	private Map<String, Integer> attributes; // TODO: (MAYBE) reimplement this as a Set (or, more efficiently, a Map) of <Attribute> (or <Attribute, Attribute>)
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
	 * @param missions A map containing, for each mission, its status (relative to the Commander).
	 */
	public Commander(String login, String commanderName, String realName,
			Map<String, Integer> attributes, int points, Map<Mission, MissionStatus> missions, boolean isAdmin) {
		if (login == null || commanderName == null || attributes == null || missions == null)
			throw new IllegalArgumentException("Argomenti null, impossibile proseguire.");
		
		this.login = login;
		this.commanderName = commanderName;
		this.attributes = attributes;
		this.points = points;
		this.realName = Optional.ofNullable(realName);
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
	 * @param missions A map containing, for each mission, its status (relative to the Commander).
	 */
	public Commander(String login, String commanderName, Map<String, Integer> attributes,
			int points, Map<Mission, MissionStatus> missions, boolean isAdmin) {
		this(login, commanderName, commanderName, attributes, points, missions, isAdmin);
	}
	
	/**
	 * Secondary constructor for Commander class, without the real name.
	 * @param login Login ID used for logging and referring to the commander.
	 * @param commanderName Commander name as used in game.
	 * @param attributes HashSet of Attribute, which stores the attributes of the commander.
	 * @param points Points the Commander has.
	 * @param missions A map containing, for each mission, its status (relative to the Commander).
	 */
	public Commander(String login, String commanderName, Map<String, Integer> attributes,
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
	public Map<String, Integer> getAttributes() {
		return attributes;
	}

	/**
	 * Setter method for the attributes.
	 * @param attributes An HashSet containing the attributes of the commander.
	 */
	public void setAttributes(Map<String, Integer> attributes) {
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

	/**
	 * Auxiliary method to check if this Commander has admin privileges.
	 * @return True if this Commander has admin privileges, false otherwise.
	 */
	public boolean isAdmin() {
		return this.isAdmin;
	}

	/**
	 * Getter method for the Map of the missions the current Commander currently has accepted.
	 * @return A Map containing the Mission(s) as keys and their relative MissionStatus as values.
	 */
	public Map<Mission, MissionStatus> getMissions() {
		return this.missions;
	}

	// TODO: implement tests on acceptMission() method
	/**
	 * Method which checks (and eventually accepts) the Mission provided for this Commander.
	 * @param mission Mission object that has to be accepted.
	 * @return True if the mission can be accepted (and accepts the mission), false otherwise.
	 */
	public boolean acceptMission(Mission mission) {
		if (mission == null) throw new IllegalArgumentException("Null Mission in acceptMission() method, aborting.");
		if (this.getPoints() >= mission.getNeededPoints()) {
			if (checkAttributes(mission)) {
				if (!this.getMissions().containsKey(mission) || this.getMissions().get(mission).equals(MissionStatus.NOT_ACCEPTED)) {
					this.addPoints(-mission.getNeededPoints());
					this.missions.put(mission, MissionStatus.PENDING);
				}
			}
		}
		return false;
	}

	private boolean checkAttributes(Mission mission) {
		for (String attr : mission.getAttributeRequirements().keySet()) {
			if (this.getAttributes().containsKey(attr)) {
				if (this.getAttributes().get(attr) < mission.getAttributeRequirements().get(attr))
					return false;
			} else return false;
		}
		return true;
	}

	/**
	 *
	 * @param mission The Mission to be completed.
	 * @param values The current situation which has to be compared to the mission.
	 * @return True if the mission can be completed (and becomes so), false otherwise.
	 */
	// TODO: completeMission method (connection to remote DB made in a servlet)
	// ERROR: ONLY ONE VALUE FOR MULTIPLE CONDITIONS (SEE BELOW METHOD meetsRequirements()). TO BE REIMPLEMENTED
	// PS: edited with a Map, but MUST be thought well
	public boolean completeMission(Mission mission,  Map<Requirement, Object> values) {
		if (this.getMissions().containsKey(mission)) {
			if (this.getMissions().get(mission).equals(MissionStatus.PENDING)) {
				if (meetsRequirements(mission, values)) {
					this.getMissions().put(mission, MissionStatus.SUCCESS);
					this.addPoints(mission.getRewardPoints());
					return true;
				}
			}
		}
		return false;
	}

	private boolean meetsRequirements(Mission mission, Map<Requirement, Object> values) {
		if (mission.getFulfillmentRequirements().size() != values.keySet().size()) return false;
		for (Requirement req : mission.getFulfillmentRequirements()) {
			if (!req.isMet(values.get(req))) return false;
		}
		return true;
	}
}
