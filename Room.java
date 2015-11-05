/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jennifermoran
 */
public class Room {

	private String description;
	private Map<String, Room> exits = new HashMap<>();// stores exits of this room.
	private List<Item> items = new ArrayList<>();// any items contained in the room, with an assigned description
	private List<Challenge> challenges = new ArrayList<>(); // any challenges contained in the room with an associated description

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 *
	 * @param description The room's description. no Item or Challenge present
	 *            in the room
	 * @param items
	 * @param challenges
	 */
	public Room(String description) {
		this.description = description;
	}

	/**
	 * Define an item or feature in this room that can be interacted with
	 *
	 * @param itemDescription - a string depicting the item or feature in the
	 *            area that can be interacted with
	 * @param item - the enum of the item or feature in the room used by the
	 *            game
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	public void setItems(ArrayList items) {
		items.addAll(items);
	}

	/**
	 * Define an item or feature in this room that can be interacted with
	 *
	 * @param challengeDescription - a string depicting an obstacle in the room,
	 *            hindering use of an exit
	 * @param challenge - the enum of the challenge in the room
	 * @param blockedExit - the String corresponding to an exit in the room
	 *            being impeeded by the challenge.
	 */
	public void addChallenge(Challenge challenge) {
		challenges.add(challenge);
	}

	/**
	 * Define an exit from this room.
	 *
	 * @param direction The direction of the exit.
	 * @param neighbor The room to which the exit leads.
	 */
	public void setExit(String direction, Room neighbor) {
		exits.put(direction, neighbor);
	}

	/**
	 * @return The short description of the room (the one that was defined in
	 *         the constructor).
	 */
	public String getShortDescription() {
		return description;
	}

	/**
	 * Return a description of the room in the form: You are in the kitchen.
	 * Exits: north west
	 *
	 * @return A long description of this room
	 */
	public String getLongDescription() {
		return "You are " + description + ".\n" + getExitString();
	}

	/**
	 * Returns a description of the room including any items or challenges in
	 * the room in the form: You are in the bedroom. There is a bottle of
	 * perfume on a nightstand.
	 *
	 * or
	 *
	 * You are in a long hallway. There is a drowsy guard blocking the eastern
	 * exit.
	 *
	 * @return A detailed examination of the room, plus the exits
	 */
	public String getRoomExamination() {
		return "You are " + description + ".\n" + getLookString() + getExitString();

	}

	/**
	 * Return a string describing the room's exits, for example "Exits: north
	 * west".
	 *
	 * @return Details of the room's exits.
	 */
	private String getExitString() {
		String returnString = "Exits:";
		for (String exit : exits.keySet()) {
			returnString += " " + exit;
		}
		return returnString;
	}

	/**
	 * Returns a string describing any items or challenges in the room, for
	 * example "There is a locked gate at the east end of the courtyard"
	 */
	private String getLookString() {
		String returnString = "";
		if (challenges.size() > 0) {
		    returnString += "Blocking your way, there is: \n";
			for (Challenge challenge : challenges) {
				returnString += challenge.getDescription() + " keeping you from going " + challenge.getBlockedExit() + "\n";
				
			}
		}
		if (items.size() > 0) {
		    if(items.size() > 1){returnString += "These ";}
		    else{returnString += "This ";}
		    returnString += "could be useful: \n";
			for (Item item : items) {
				returnString += item.getDescription() + " \n";
			}
		}
		if (challenges.size() == 0 && items.size() == 0) {
			returnString = "There is nothing remarkable here\n";
		}

		return returnString;
	}

	/**
	 * a method to test to see if a room has an item of a certain type, if so
	 * returns that item, otherwise returns null
	 */
	public Item hasItem(ItemType itemType) {
		if (items.size() > 0) {
			for (Item item : items) {
				if (item.getType() == itemType) {
					return item;
				}
			}
		}
		return null;
	}

	/**
	 * a method to remove an item from a room after it has been taken by a
	 * player returns the item in question
	 *
	 * @param itemIWant
	 * @return
	 */
	public Item takeItem(ItemType itemIWant) {
		if (items.size() > 0 && this.hasItem(itemIWant) != null) {
			Item itemToRemove = this.hasItem(itemIWant);
			items.remove(itemToRemove);
			return itemToRemove;
		} else {
			System.out.println("That item is not in this room\n");
			return null;
		}
	}

	
	/**
	 * Test to see if player can move out of this room unimpeeded
	 *
	 * @param exitToTest
	 * @return
	 */
	public boolean canExit(String exitToTest) {
		boolean wayIsClear = true;
		if (challenges.size() > 0) {
			for (Challenge challenge : challenges) {
				if (challenge.getBlockedExit().equals(exitToTest)) {
					wayIsClear = false;
				}
			}
		}
		return wayIsClear;
	}

	/**
	 * returns the description of challenges in the room
	 *
	 * @return
	 */
	public String getChallengeText() {
		String returnString = "";
		if (challenges.size() > 0) {
			for (Challenge challenge : challenges) {
				returnString += challenge.getDescription() + ".\n";
			}
		} else {
			returnString = "There is no challenge in this room!";
		}
		return returnString;
	}

	/**
	 * Return the room that is reached if we go from this room in direction
	 * "direction". If there is no room in that direction, return null.
	 *
	 * @param direction The exit's direction.
	 * @return The room in the given direction.
	 */
	public Room getExit(String direction) {
		return exits.get(direction);
	}
}
