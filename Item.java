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

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
