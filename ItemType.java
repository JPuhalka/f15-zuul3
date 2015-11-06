/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jennifermoran
 */
public enum ItemType {

	FOOD("food"), PERFUME("perfume"), COLOGNE("cologne"), KEY("key"), BATH("bath"), SUIT("suit"), DRESS("dress"), UNKNOWN("unknown");
	String id;
	private static final Map<String, ItemType> lookup = new HashMap<>();

	static {
		for (ItemType itemType : EnumSet.allOf(ItemType.class)) {
			lookup.put(itemType.getId(), itemType);
		}
	}

	ItemType(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public static ItemType getItemType(String id) {
		ItemType itemType = (id != null) ? lookup.get(id) : UNKNOWN;
		if (itemType == null) {
			itemType = UNKNOWN;
		}
		return itemType;
	}

	public static boolean isValidItem(String id) {
		ItemType itemType = getItemType(id);
		return itemType != UNKNOWN;
	}

	public static void showAllItems() {
		for (Map.Entry<String, ItemType> itemType : lookup.entrySet()) {
			if (itemType.getValue() != UNKNOWN) {
				System.out.print(itemType.getKey() + "  ");
			}
		}
		System.out.println();
	}

}
