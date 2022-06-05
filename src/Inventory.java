import java.util.Scanner;

/**
 * Class to manage an inventory of FoodItems.
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class Inventory {
	
/* Member Variables */
	
	/**
	 * The maximum size of the Inventory.
	 */
	private static final int INV_SIZE = 20;
	
	/**
	 * The collection of FoodItems stored in the Inventory.
	 */
	private FoodItem[] inventory;
	
	/**
	 * The number of FoodItems in the Inventory.
	 */
	private int numItems;

	
/* Constructors */
	
	/**
	 * Default constructor.
	 */
	public Inventory() {
		
		inventory = new FoodItem[INV_SIZE];		// initialize inventory array with max size
		numItems = 0;							// no items yet
	}
	
	
/* Methods */
	
	/**
	 * Adds an item to the Inventory array.
	 * @param scanner - user input stream
	 * @return true if program successfully reads in all data, otherwise returns false
	 */
	public boolean addItem(Scanner scanner) {
		// TODO: implement Inventory::addItem()
		
		return false;	// placeholder
	}
	
	/**
	 * Searches the inventory for a FoodItem with the same itemCode as in the item parameter.
	 * @param item - the FoodItem whose itemCode to search for
	 * @return the index of the item if it exists or -1.
	 */
	public int alreadyExists(FoodItem item) {
		// TODO: implement Inventory::alreadyExists()
		
		return -1;	// placeholder
	}
	
	/**
	 * Reads in an itemCode to update and quantity to update by and updates that item 
	 * by the input quantity in the Inventory array. 
	 * @param scanner - user input stream
	 * @param buyOrSell - whether a buying (true) or selling (false) operation is occurring.
	 * @return true/false on whether update was successful or not
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		// TODO: implement Inventory::updateQuantity()
		
		return false;	// placeholder
	}
	
	/**
	 * @return the string representation of the Inventory.
	 */
	@Override
	public String toString() {
		// TODO: implement Inventory::toString()
		
		return "";	// placeholder
	}
}
