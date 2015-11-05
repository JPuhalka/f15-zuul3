/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jennifermoran
 */
public class Room {

	private String description;
	private Map<ExitType, Room> exits = new HashMap<>();
	private Map<ItemType, Item> items = new HashMap<>();
	private Challenge challenge;

	/**
	 * Constructor
	 *
	 * @param description - Room description
	 */
	public Room(String description) {
		this.description = description;
	}

	/**
	 * Description getter
	 *
	 * @return room description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Description setter
	 *
	 * @param description - room description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Exits getter
	 *
	 * @return map of exit type and room object which defines new room after
	 *         exiting
	 */
	public Map<ExitType, Room> getExits() {
		return exits;
	}

	/**
	 * Exits setter
	 *
	 * @param exits - map of exit type and room object which defines new room
	 *            after exiting
	 */
	public void setExits(Map<ExitType, Room> exits) {
		this.exits = exits;
	}

	/**
	 * Items getter
	 *
	 * @return map of item objects found in room
	 */
	public Map<ItemType, Item> getItems() {
		return items;
	}

	/**
	 * Single Items getter
	 *
	 * @param key - key for lookup
	 * @return item lookup by key
	 */
	public Item getItem(ItemType key) {
		return items.get(key);
	}

	/**
	 * Items setter
	 *
	 * @param items - list of item objects found in room
	 */
	public void setItems(Map<ItemType, Item> items) {
		this.items = items;
	}

	/**
	 * Challenge getter
	 *
	 * @return challenge
	 */
	public Challenge getChallenge() {
		return challenge;
	}

	/**
	 * Challenge Setter
	 *
	 * @param challenge
	 */
	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	/**
	 * Add a single item to the room
	 *
	 * @param item - item object
	 */
	public void addItem(Item item) {
		items.put(item.getType(), item);
	}

	/**
	 * Add an exit from this room
	 *
	 * @param direction - exit type of direction
	 * @param neighbor - room object of new room after exiting
	 */
	public void addExit(ExitType direction, Room neighbor) {
		exits.put(direction, neighbor);
	}

	/**
	 * Retrieve a description of room and all exits
	 *
	 * @return long description
	 */
	public String getLongDescription() {
		return "You are " + description + ".\n" + showAllExits();
	}

	/**
	 * Retrieve a description of room including all items and all exits
	 *
	 * @return detailed room description
	 */
	public String getRoomExamination() {
		return "You are " + getDescription() + ".\n" + showAllItems() + showChallenge() + showAllExits();

	}

	/**
	 * Retrieve a description of all exits
	 *
	 * @return exit details
	 */
	private String showAllExits() {
		StringBuilder sBuffer = new StringBuilder();
		sBuffer.append("Exits: ");
		for (Map.Entry<ExitType, Room> exit : exits.entrySet()) {
			sBuffer.append(exit.getKey().getId()).append(" ");
		}
		return sBuffer.toString();
	}

	/**
	 * Retrieve a description of all items
	 *
	 * @return item details or if none then a default message
	 */
	private String showAllItems() {
		StringBuilder sBuffer = new StringBuilder();

		if (items.isEmpty()) {
			sBuffer.append("There is nothing remarkable here\n");
		} else {
			sBuffer.append("Items: ");
			for (Map.Entry<ItemType, Item> item : items.entrySet()) {
				sBuffer.append(item.getKey().getId()).append(" ");
			}
			sBuffer.append("\n");
		}
		return sBuffer.toString();
	}

	private String showChallenge() {
		StringBuilder sBuffer = new StringBuilder();
		if (challenge != null) {
			sBuffer.append("Blocking your way, there is: \n");
			sBuffer.append(challenge.getDescription()).append(" keeping you from going ").append(challenge.getBlockedExit()).append("\n");
		}
		return sBuffer.toString();
	}

	/**
	 * Return boolean whether an item is located in the room
	 *
	 * @param key - key for lookup
	 * @return true or false
	 */
	public boolean hasItem(ItemType key) {
		return items.containsKey(key);
	}

	/**
	 * Take an item out of the room - if it is not located in the room print a
	 * message
	 *
	 * @param key - item being removed
	 */
	public void takeItem(ItemType key) {
		if (hasItem(key)) {
			items.remove(key);
		} else {
			System.out.println("That item is not in this room\n");
		}
	}

	/**
	 * Test to see if player can move out of this room unimpeeded
	 *
	 * @param exitToTest
	 * @return
	 */
	public boolean canExit(ExitType exitToTest) {
		return (challenge == null ? true : challenge.getBlockedExit().equals(exitToTest));
	}

	/**
	 * Retrieve the next room based on the exit direction
	 *
	 * @param direction - direction of exit
	 * @return room object of next room
	 */
	public Room getNextRoom(ExitType direction) {
		return exits.get(direction);
	}
}
