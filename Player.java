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
    private int hunger = 0; // represents the level of hunger of the player
    private final int fatalHunger = 30;   // the level of hunger at which the player die
    private final int hungerWarning = fatalHunger / 3; // the level of hunger at which the player is warned
    private Room currentRoom;
    private double carryWeight; // the weight of the current inventory
    private double weightLimit = 80; // the max weight the player can carry
    private boolean isDead = false;  

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
        this.carryWeight = 0;
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
     * a method to check if an item in the player' inventory
     */
    public Item hasItem(ItemType neededItem){
		if (inventory.size() > 0) {
			for (Item item : inventory) {
				if (item.getType() == neededItem) {
					return item;
				}
			}
		}
		return null;
    }

    /**
     * a method to add an item to the player's inventory
     */
    public boolean addItem(Item newItem) {
        if(this.carryWeight + newItem.getWeight() <= this.weightLimit){
            this.inventory.add(newItem);
            this.carryWeight += newItem.getWeight();
            return true;
        }else{
             System.out.println("You are carrying too much to add this item! Put something down first!");
             return false;
        }
    }

        /**
     * a method to remove an item to the player's inventory
     */
    public Item removeItem(ItemType droppedItem) {
        Item itemToDrop = this.hasItem(droppedItem);
        if(itemToDrop!=null){
            this.inventory.remove(itemToDrop);
            this.carryWeight -= itemToDrop.getWeight();
            return itemToDrop;
        }else{
             System.out.println("You are not carrying one of those!");
             return null;
        }
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
     * a method to give an analysis of the player's inventory
     */
    public void showInventory(){
        if(!inventory.isEmpty()){
            System.out.println("You are carrying the following items:");
        
            for(Item item : inventory){
                System.out.println(" A " + item.getDescription() + " that weighs " + item.getWeight() + " units");
            }
        }else{
            System.out.println("You are not currently carrying anything!");
        }
    }

    /**
     * a method to set the players current room, calls checkHunger
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * a method to increment the player's hunger status and 
     */
    public boolean checkHunger(){
    this.hunger++;
        if(this.hunger > this.fatalHunger){
            System.out.println("You can't go on anymore, you have died from hunger!!!");
            return false;
        }else if(this.hunger == this.fatalHunger){
            System.out.println("You feel sharp pains and you barely have the strength to move anymore, you must eat now!!!");
        }else if(this.hunger == this.hungerWarning){
            System.out.println("Your stomach is growling, it might be a good idea to find some food!!!");
        }
        return true;
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
    public boolean isLiving(){
        return isDead;
    }
}
