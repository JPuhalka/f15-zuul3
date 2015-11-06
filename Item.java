/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jennifermoran
 */
public class Item {

	private ItemType type;
	private String description;
	private double weight;

	/**
	 *
	 * @param type
	 * @param description
	 * @param weight
	 */
	public Item(ItemType type, String description, double weight) {
		this.type = type;
		this.description = description;
		this.weight = weight;
	}

	/**
	 * Type getter
	 *
	 * @return item type enum
	 */
	public ItemType getType() {
		return type;
	}

	/**
	 * Type setter
	 *
	 * @param type
	 */
	public void setType(ItemType type) {
		this.type = type;
	}

	/**
	 * Description getter
	 *
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Description setter
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Weight getter
	 *
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Weight setter
	 *
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
