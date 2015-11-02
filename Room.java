import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Escape with Dignity" application. 
 * 
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @authors Jennifer Puhalka and Andrew Worthington
 * @version 2015.11.02
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;         // any items contained in the room, with an assigned description
    private ArrayList<Challenge> challenges;  // any challenges contained in the room with an associated description   

    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     *      no Item or Challenge present in the room
     */
    public Room(String description) 
    {
        this.description = description;
        this.items = new ArrayList<Item>();
        this.challenges = new ArrayList<Challenge>();
        exits = new HashMap<String, Room>();
    }
    
    /**
     * Define an item or feature in this room that can be interacted with
     * @param itemDescription - a string depicting the item or feature in the area that can be interacted with
     * @param item - the enum of the item or feature in the room used by the game
     */
    public void addItem(String itemDescription, ItemType item){
        this.items.add(new Item(itemDescription, item));
    }
    
    /**
     * Define an item or feature in this room that can be interacted with
     * @param challengeDescription - a string depicting an obstacle in the room, hindering use of an exit
     * @param challenge - the enum of the challenge in the room
     * @param blockedExit - the String corresponding to an exit in the room being impeeded by the challenge.
     */
    public void addChallenge(String challengeDescription, ChallengeType challenge, String blockedExit){
        this.challenges.add(new Challenge(challengeDescription, challenge, blockedExit));
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Returns a description of the room including any items or challenges in the room in the form:
     *    You are in the bedroom.
     *    There is a bottle of perfume on a nightstand.
     *    
     *    or
     *    
     *    You are in a long hallway.
     *    There is a drowsy guard blocking the eastern exit.
     *    
     *    @return A detailed examination of the room, plus the exits
     */
    public String getRoomExamination()
    {
        return "You are " + description + ".\n" + getLookString() + getExitString();
        
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Returns a string describing any items or challenges in the room, for example
     * "There is a locked gate at the east end of the courtyard"
     */
    private String getLookString()
    {
        String returnString = "";
        if(challenges.size()>0){
            for(Challenge challenge : challenges) {
                returnString += challenge.getDescription() + ".\n";
            }
        }
        if(items.size()>0){
            for(Item item : items) {
                returnString += item.getDescription() + ".\n";
            } 
        }if(challenges.size() == 0 && items.size() == 0){
            returnString = "There is nothing remarkable here";
        }
        
        return returnString;
    }
    
    /**
     * Test to see if player can move out of this room unimpeeded
     */
    public boolean canExit(String exitToTest){
        boolean wayIsClear = true;
        if(challenges.size()>0){
            for(Challenge challenge : challenges){
                if(challenge.getBlockedExit().equals(exitToTest))
                    wayIsClear = false;
            }
        }
        return wayIsClear;
    }
    
    /**
     * returns the description of challenges in the room
     */
    public String getChallengeText(){
        String returnString = "";
        if(challenges.size()>0){
            for(Challenge challenge : challenges){
               returnString += challenge.getDescription() + ".\n";
            }
        }else{
            returnString = "There is no challenge in this room!";
        }
        return returnString;
    }
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

