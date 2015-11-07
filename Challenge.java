/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * This class holds information about the challenge that a user faces.
 *
 * @author Jennifer M and Andrew and Jennifer P
 * @version 2015.11.06
 */
public class Challenge {

	private final ChallengeType challengeType; // the type of the challenge
	private final String description; // information about the object in a room
	private final ExitType blockedExit; // the name of the exit being blocked in a room
	private final ItemType resolution; // the resolution needed to pass the challenge

	/**
	 * Constructor for objects of class Challenge
	 *
	 * @param description, the description of the challenge
	 * @param challengeType what type of
	 * @param exit
	 * @param resolution
	 */
	public Challenge(String description, ChallengeType challengeType, ExitType exit, ItemType resolution) {
		this.description = description;
		this.challengeType = challengeType;
		this.blockedExit = exit;
		this.resolution = resolution;
	}

	/**
	 * Description Getter
	 *
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ChallengeType Getter
	 *
	 * @return ChallengeType
	 */
	public ChallengeType getChallengeType() {
		return this.challengeType;
	}

	/**
	 * Blocked Exit Getter
	 *
	 * @return ExitType
	 */
	public ExitType getBlockedExit() {
		return blockedExit;
	}

	/**
	 * Resolution Getter
	 *
	 * @return ItemType
	 */
	public ItemType getResolution() {
		return resolution;
	}

}
