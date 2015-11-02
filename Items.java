import java.util.HashMap;

/**
 * This class is part of the "Escape with Dignity" application. 
 * 
 * This class holds an enumeration of all items and room features known to the game.
 * It is used to recognise them as they are typed in.
 *
 * @authors Jennifer Puhalka and Andrew Worthington
 * @version 2015.11.02
 */
public class Items
{
    // instance variables - replace the example below with your own
    private HashMap<String, ItemType>validItems;

    /**
     * Constructor - establish the hashmap of items
     */
    public Items()
    {
        validItems = new HashMap<String, ItemType>();
        validItems.put("bath", ItemType.BATH);
        validItems.put("perfume", ItemType.PERFUME);
        validItems.put("cologne", ItemType.COLOGNE);
        validItems.put("key", ItemType.KEY);
        validItems.put("suit", ItemType.SUIT);
        validItems.put("food", ItemType.FOOD);
    }

    /**
     * Find the Item associated with an item string.
     * @param item The word to look up (as a string).
     * @return The Item correspondng to item, or UNKNOWN
     *         if it is not a valid command word.
     */
    public ItemType getItemType(String itemTypeString)
    {
        ItemType itemType = validItems.get(itemTypeString);
        if(itemType != null) {
            return itemType;
        }
        else {
            return ItemType.UNKNOWN;
        }
    }
    
    /**
     * Check whether a given String is a valid item. 
     * @return true if it is, false if it isn't.
     */
    public boolean isItem(String aString)
    {
        return validItems.containsKey(aString);
    }

    /**
     * Print all valid items to System.out.
     */
    public void showAll() 
    {
        for(String item : validItems.keySet()) {
            System.out.print(item + "  ");
        }
        System.out.println();
    }
}