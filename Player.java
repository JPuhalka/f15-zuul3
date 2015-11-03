/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.util.ArrayList;

/**
 *
 * @author jennifermoran
 */
public class Player {

	private ArrayList<String> moveHistory;
	private ArrayList<Item> inventory;
	private String scent;
	private String clothing;
	private int hunger; // represents the level of hunger of the player
	private Room currentRoom;

	/**
	 * Constructor for objects of class Player takes no parameters, but sets up
	 * initial condition of player
	 */
	public Player() {
		this.inventory = new ArrayList<>(); // starts out carrying nothing;
		this.moveHistory = new ArrayList<>(); // starts out with no moves
		this.scent = "foul"; // the player starts out in a stable ... he smells like shit
		this.clothing = "naked"; // the player starts out naked, sucks for him
		this.hunger = 0; // the player doesn't start out hungry.
	}

	public ArrayList<String> getMoveHistory() {
		return moveHistory;
	}

	public void setMoveHistory(ArrayList<String> moveHistory) {
		this.moveHistory = moveHistory;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
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
	 * a method to add an item to the player's inventory
	 */
	public void addItem(Item item) {
		this.inventory.add(item);
	}

	/**
	 * a method to add a go move to the player's history
	 */
	public void addMove(String move) {
		moveHistory.add(move);
	}

	/**
	 * a method to change the scent status of the player
	 */
	public void changeScent(String scent) {
		this.scent = scent;
	}

	/**
	 * a method to set the players current room
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
}
