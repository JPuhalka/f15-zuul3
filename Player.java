import java.util.ArrayList;

/**
 * This class is part of the "Escape with Dignity" application. 
 * 
 * This class represents the player and is used to track inventory and status conditions
 * including hunger, dress, and odor
 *
 * @authors Jennifer Puhalka and Andrew Worthington
 * @version 2015.11.02
 */
public class Player
{
    private ArrayList<Item> inventory;
    private String scent;
    private String clothing;
    private int hunger; // represents the level of hunger of the player

    /**
     * Constructor for objects of class Player
     * takes no parameters, but sets up initial condition of player
     */
    public Player()
    {
        this.inventory = new ArrayList<Item>(); // starts out carrying nothing;
        this.scent = "foul";  // the player starts out in a stable ... he smells like shit
        this.clothing = "naked"; // the player starts out naked, sucks for him
        this.hunger = 0; // the player doesn't start out hungry.
    }

    /**
     * a method to retrieve the level of hunger of the player
     */
    public int getHunger()
    {
       return hunger;
    }
    
    /**
     * a method to change the clothing status of the player
     */
    public void changeClothes(String clothing)
    {
        this.clothing = clothing;
    }
    
    /**
     * a method to add an item to the player's inventory
     */
    public void addItem(Item item){
        this.inventory.add(item);
    }
    
    /**
     * a method to change the scent status of the player
     */
    public void changeScent(String scent)
    {
        this.scent = scent;
    }
}
