/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jennifermoran
 */
public class Player {

	private ArrayList<Room> moveHistory;
	private Map<ItemType, Item> inventory;
	private String scent;
	private String clothing;
	private int hunger = 0; // represents the level of hunger of the player
	private final int fatalHunger = 6; // the level of hunger at which the player die
	private final int hungerWarning = fatalHunger / 3; // the level of hunger at which the player is warned
	private Room currentRoom;
	private double carryWeight; // the weight of the current inventory
	private double weightLimit = 80; // the max weight the player can carry
	private boolean isAlive = true;

	/**
	 * Constructor for objects of class Player takes no parameters, but sets up
	 * initial condition of player
	 */
	public Player() {
		this.inventory = new HashMap<>(); // starts out carrying nothing;
		this.moveHistory = new ArrayList<>(); // starts out with no moves
		this.scent = "foul"; // the player starts out in a stable ... he smells like shit
		this.clothing = "naked"; // the player starts out naked, sucks for him
		this.hunger = 0; // the player doesn't start out hungry.
		this.carryWeight = 0;
	}

	public ArrayList<Room> getMoveHistory() {
		return moveHistory;
	}

	public void setMoveHistory(ArrayList<Room> moveHistory) {
		this.moveHistory = moveHistory;
	}

	public Map<ItemType, Item> getInventory() {
		return inventory;
	}

	public void setInventory(Map<ItemType, Item> inventory) {
		this.inventory = inventory;
	}

	public String getScent() {
		return scent;
	}

	public void setScent(String scent) {
		this.scent = scent;
	}

	public String getClothing() {
		return clothing;
	}

	public void setClothing(String clothing) {
		this.clothing = clothing;
	}

	/**
	 * a method to attempt to eat a piece of food the player is carrying,
	 * returns true if successful
	 */
	public boolean eatFood() {
		boolean ateFood = false;
		if (this.hunger <= 0) {
			System.out.println("You shouldn't eat yet, you're not hungry!");
			return false;
		}

		for (Map.Entry<ItemType, Item> inventoryItem : inventory.entrySet()) {
			if (inventoryItem.getKey() == ItemType.FOOD && !ateFood) {
				System.out.println("You remove " + inventoryItem.getValue().getDescription() + " from your pack and eat it");
				hunger = 0;
				inventory.remove(inventoryItem.getKey());
				return true;
			}
		}

		if (!ateFood) {
			System.out.println("You have no food to eat! Find some!");
			return false;
		}
		return false;
	}

	/**
	 * a method to increment the player's hunger status and
	 */
	public boolean checkHunger() {
		this.hunger++;
		if (this.hunger > this.fatalHunger) {
			System.out.println("You can't go on anymore, you have died from hunger!!!");
			this.isAlive = false;
			return false;
		} else if (this.hunger == this.fatalHunger) {
			System.out.println("You feel sharp pains and you barely have the strength to move anymore, you must eat now!!!");
		} else if (this.hunger == this.hungerWarning) {
			System.out.println("Your stomach is growling, it might be a good idea to find some food!!!");
		}
		return true;
	}

	/**
	 * a method to retrieve the level of hunger of the player
	 */
	public int getHunger() {
		return hunger;
	}

	/**
	 * a method to change the clothing status of the player
	 */
	public void changeClothes(String clothing) {
		this.clothing = clothing;
	}

	/**
	 * Return boolean whether an item is located in inventory
	 *
	 * @param key - key for lookup
	 * @return true or false
	 */
	public boolean hasInventory(ItemType key) {
		return inventory.containsKey(key);
	}

	/**
	 * Single Inventory getter
	 *
	 * @param key - key for lookup
	 * @return item lookup by key
	 */
	public Item getInventory(ItemType key) {
		return inventory.get(key);
	}

	/**
	 * Take an item out of the players inventory - if it is not located in the
	 * inventory print a message
	 *
	 * @param key - item being removed
	 */
	public void removeInventory(ItemType key) {
		if (hasInventory(key)) {
			carryWeight -= getInventory(key).getWeight();
			inventory.remove(key);
		} else {
			System.out.println("That item is not in your inventory\n");
		}
	}

	/**
	 * Add an item to a players inventory as long as they are under the weight
	 * limit - if not print a message
	 *
	 * @param item
	 */
	public void addInventory(Item item) {
		if (carryWeight + item.getWeight() <= weightLimit) {
			inventory.put(item.getType(), item);
			carryWeight += item.getWeight();
		} else {
			System.out.println("You are carrying too much weight to add this item! Put something down first!");
		}
	}

	/**
	 * a method to add a go move to the player's history
	 */
	public void addMove(Room move) {
		moveHistory.add(move);
	}

	/**
	 * a method to change the scent status of the player
	 */
	public void changeScent(String scent) {
		this.scent = scent;
	}

	/**
	 * a method to give an analysis of the player's inventory
	 */
	public String showInventory() {
		StringBuilder sBuffer = new StringBuilder();

		if (inventory.isEmpty()) {
			System.out.println("You are not currently carrying anything!");
		} else {
			sBuffer.append("Items: ");
			for (Map.Entry<ItemType, Item> inventoryItem : inventory.entrySet()) {
				sBuffer.append(inventoryItem.getKey().getId()).append(" - ").append(inventoryItem.getValue().getDescription()).append(" that weighs ")
						.append(inventoryItem.getValue().getWeight()).append(" units");
			}
			sBuffer.append("\n");
		}
		return sBuffer.toString();
	}

	/**
	 * a method to set the players current r
	 *
	 * @param room, calls checkHunger
	 */
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}

	/**
	 * a method to set the players current room
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * tell the game if the player is alive or not
	 */
	public boolean isLiving() {
		return this.isAlive;
	}
}
