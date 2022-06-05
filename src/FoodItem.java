import java.util.Scanner;

/**
 * Class to represent a food product that can be stored in an Inventory. 
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class FoodItem {
	
/* Member Variables */
	
	/**
	 * A unique numerical identifier.
	 */
	private int itemCode;
	
	/**
	 * A name to display.
	 */
	private String itemName;
	
	/**
	 * The retail price of the FoodItem.
	 */
	private float itemPrice;
	
	/**
	 * The quantity of this FoodItem in the Inventory.
	 * Can never be less than 0.
	 */
	private int itemQuantityInStock;
	
	/**
	 * The wholesale cost of the FoodItem.
	 */
	private float itemCost;
	
	
/* Constructors */
	
	public FoodItem() {

		this.itemCode = -1;
		this.itemName = "Invalid item";
	}
	
	
/* Methods */
	
	/**
	 * Reads from the Scanner object passed in and fills the data member fields of the class with valid data
	 * @param scanner - user input stream
	 * @return true if program successfully reads in all fields, otherwise returns false
	 */
	public boolean addItem(Scanner scanner) {
		// TODO: implement FoodItem::addItem()
		
		return false;	// placeholder
	}
	
	/**
	 * Reads a valid itemCode from the Scanner object.
	 * @param scanner - user input stream
	 * @return true/false if successful
	 */
	public boolean inputCode(Scanner scanner) {
		// TODO: implement FoodItem::inputCode()
		
		return false;	// placeholder
	}
	
	/**
	 * @param item - the item to compare
	 * @return true if the itemCode of the object being acted on and the item object parameter are the same value
	 */
	public boolean isEqual(FoodItem item) {
		// TODO: implement FoodItem::isEqual()
		
		return false;	// placeholder
	}
	
	/**
	 * Updates the quantity field by amount (positive or negative)
	 * @param amount - the updated amount
	 * @return true if successful, otherwise returns false
	 */
	public boolean updateItem(int amount) {
		// TODO: implement FoodItem::updateItem()
		
		return false;	// placeholder
	}
	
	/**
	 * @return all the data members in the class in String format. 
	 */
	@Override
	public String toString() {
		// TODO: implement FoodItem::toString()
		
		return "";	// placeholder
	}
}
