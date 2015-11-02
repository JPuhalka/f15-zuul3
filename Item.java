
/**
 * Class Item - an Item in an Adventure game.
 *
 * This class is part of the "Escape with Dignity" application. 
 * 
 * An "Item" represents one object a player needs to hold or interact with to overcome obstacles
 * 
 * @authors Jennifer Puhalka and Andrew Worthington
 * @version 2015.11.02
 */
public class Item
{
    private ItemType itemType; // the type of the item
    private String roomDescription;  // information about the object in a room
    private String itemDescription;  // information about the item relevant to the player

    /**
     * Constructor for objects of class Item
     * @param description, the description of the item
     */
    public Item(String roomDescription, ItemType itemType, String itemDescription)
    {
        this.roomDescription = roomDescription;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
    }

    /**
     * a method to return the description of the item in the scene
     */
    public String getRoomDescription()
    {
        return this.roomDescription;
    }

    
    /**
     * a method to return the description of the item in the player's inventory
     */
    public String getItemDescription()
    {
        return this.itemDescription;
    }
    
    
    /**
     * a method to return the type of the item
     */
    public ItemType getItemType()
    {
        return this.itemType;
    }
}
