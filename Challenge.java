/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jennifermoran
 */
public class Challenge {

	private final ChallengeType challengeType; // the type of the challenge
	private final String description; // information about the object in a room
	private final ExitType blockedExit; // the name of the exit being blocked in a room

	/**
	 * Constructor for objects of class Challenge
	 *
	 * @param description, the description of the challenge
	 * @param challengeType
	 * @param exit
	 */
	public Challenge(String description, ChallengeType challengeType, ExitType exit) {
		this.description = description;
		this.challengeType = challengeType;
		this.blockedExit = exit;
	}

	/**
	 * a method to return the description of the challenge
	 *
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * a method to return the type of the challenge
	 *
	 * @return ChallengeType
	 */
	public ChallengeType getChallengeType() {
		return this.challengeType;
	}

	/**
	 * a method to return the name of the exit blocked by this challenge
	 *
	 * @return String
	 */
	public ExitType getBlockedExit() {
		return blockedExit;
	}
}
