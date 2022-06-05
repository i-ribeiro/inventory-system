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

/* addItem Constants */
	
	/**
	 * Input expected to add a Fruit product.
	 */
	private static final String ADD_FRUIT	= "f";
	
	/**
	 * Input expected to add a Vegetable product.
	 */
	private static final String ADD_VEG		= "v";
	
	/**
	 * Input expected to add a Preserve product.
	 */
	private static final String ADD_PRE		= "p";
	
	/**
	 * Input expected to add a Meat product.
	 */
	private static final String ADD_MEAT	= "m";

	
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
	 * O(1) time complexity.
	 * @param scanner - user input stream
	 * @return true if program successfully reads in all data, otherwise returns false
	 */
	public boolean addItem(Scanner scanner) {
		
		/* early out if inventory is full */
		
		if (this.numItems >= INV_SIZE) {
			System.out.println("Inventory full");
			return false;
		}
		
		
		/* input item type and initialize toAdd */
		
		FoodItem toAdd = null;	// polymorphic item to add to Inventory; initialized based on user input
		
		do {
			
			/* prompt item type input */
			
			System.out.printf(
					"Do you wish to add a fruit(%s), vegetable(%s), preserve(%s) or meat(%s)? ",
					ADD_FRUIT,
					ADD_VEG,
					ADD_PRE,
					ADD_MEAT);
			
			String choice = scanner.next();	// input choice
			
			
			/* add new item of type input */
			
			switch (choice) {
			case ADD_FRUIT:				// add a Fruit product
				toAdd = new Fruit();
				break;
				
			case ADD_VEG:				// add a Vegetable product
				toAdd = new Vegetable();
				break;
			
			case ADD_PRE:				// add a Preserve product
				toAdd = new Preserve();
				break;
				
			case ADD_MEAT:				// add a Meat product
				toAdd = new Meat();
				break;
				
			default:					// input is invalid
				System.out.println("Invalid entry");
				break;
			}
			
		/* exit loop once toAdd has been initialized */
		} while(toAdd == null);
		
		
		/* initialize new item with input */
		boolean result = toAdd.addItem(scanner);
		
		/* add item if input successful */
		if (result == true)	this.inventory[this.numItems++] = toAdd;
		
		
		return result;
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
		
		StringBuilder sb = new StringBuilder("Inventory:\n");
		
		for (int i = 0; i < this.numItems; ++i)		// for each FoodItem in Inventory
			sb.append(inventory[i] + "\n");					// append the item
		return sb.toString();
	}
}
