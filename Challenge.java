
/**
 * Class Challenge - an Challenge in an Adventure game.
 *
 * This class is part of the "Escape with Dignity" application. 
 * 
 * An "Challenge" represents one object a player needs to hold or interact with to overcome obstacles
 * 
 * @authors Jennifer Puhalka and Andrew Worthington
 * @version 2015.11.02
 */
public class Challenge
{
    private ChallengeType challengeType; // the type of the challenge
    private String description;  // information about the object in a room
    private String blockedExit; // the name of the exit being blocked in a room

    /**
     * Constructor for objects of class Challenge
     * @param description, the description of the challenge
     */
    public Challenge(String description, ChallengeType challengeType, String exit)
    {
        this.description = description;
        this.challengeType = challengeType;
        this.blockedExit = exit;
    }

    /**
     * a method to return the description of the challenge
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * a method to return the type of the challenge
     */
    public ChallengeType getChallengeType()
    {
        return this.challengeType;
    }
    
    /**
     * a method to return the name of the exit blocked by this challenge
     */
    public String getBlockedExit()
    {
        return blockedExit;
    }
}
