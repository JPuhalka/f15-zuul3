
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
    private String description;  // information about the object in a room

    /**
     * Constructor for objects of class Item
     * @param description, the description of the item
     */
    public Item(String description, ItemType itemType)
    {
        this.description = description;
        this.itemType = itemType;
    }

    /**
     * a method to return the description of the item
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * a method to return the type of the item
     */
    public ItemType getItemType()
    {
        return this.itemType;
    }
}
