import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
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
	
	
/* updateQuantity Constants */
	
	/**
	 * Value used to indicate that the update is a buy.
	 */
	public static final boolean UPDATE_BUY	= true;
	
	/**
	 * Value used to indicate that the update is a sell.
	 */
	public static final boolean UPDATE_SELL	= false;
	

/* Member Variables */
	
	/**
	 * The ensured size of the Inventory.
	 */
	private static final int INV_SIZE = 20;
	
	/**
	 * The collection of FoodItems stored in the Inventory.
	 */
	private ArrayList<FoodItem> inventory;

	
/* Constructors */
	
	/**
	 * Default constructor.
	 */
	public Inventory() {
		
		inventory = new ArrayList<FoodItem>();		// initialize inventory array
		inventory.ensureCapacity(INV_SIZE);
	}
	
	
/* Methods */
	
	/**
	 * Adds an item to the Inventory array.
	 * O(n * log * n) time complexity.
	 * @param scanner - user input stream
	 * @return true if program successfully reads in all data, otherwise returns false
	 */
	public boolean addItem(Scanner scanner) {
		
		/* early out if inventory is full */
		
		if (this.inventory.size() >= INV_SIZE) {
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
		
		
		/* input itemCode and check for duplicates */
		System.out.print("Enter the code for the item: ");
		boolean result = toAdd.inputCode(scanner);
		
		if (this.alreadyExists(toAdd) >= 0 || result == false) {	// unsuccessful if code already exists or if code input fails
			
			System.out.println("Item code already exists");
			return false;
		}
		
		
		/* initialize new item with input */
		result = toAdd.addItem(scanner);
		
		
		/* add item if input successful */
		if (result == true)	this.inventory.add(toAdd);
		
		
		/* sort by itemCode, null values left at end */
		inventory.sort(new SortByItemCode());
		
		return result;
	}
	
	/**
	 * Searches the inventory for a FoodItem with the same itemCode as in the item parameter.
	 * O(n * log * n) time complexity.
	 * @param item - the FoodItem whose itemCode to search for
	 * @return the index of the item if it exists or negative value.
	 */
	public int alreadyExists(FoodItem item) {
		
		// early out if inventory is empty
		if (this.inventory.size() == 0) return -1;
		
		// binary search for item, negative if not found
		return this.recursiveBinarySearch(this.inventory, item, 0, this.inventory.size() - 1);
	}
	
	/**
	 * Reads in an itemCode to update and quantity to update by and updates that item 
	 * by the input quantity in the Inventory array. 
	 * @param scanner - user input stream
	 * @param buyOrSell - whether a buying (UPDATE_BUY) or selling (UPDATE_SELL) operation is occurring.
	 * @return true/false on whether update was successful or not
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		
		if (this.inventory.size() == 0)	return false;	// early out if there are no items
		
		int index = -1;
		int quantity = -1;
		
		/* input item code */
		
		FoodItem searchItem = new FoodItem();
		
		System.out.print("Enter the code for the item: ");
		boolean codeValid = searchItem.inputCode(scanner);
		
		if (codeValid == false)	return false;	// unsuccessful if code is not entered
		
		index = this.alreadyExists(searchItem);
		
		if (index == -1) {		// early out if item not found
			
			System.out.println("Code not found in inventory...");
			return false;
		}
		
		
		/* input quantity */
		
		boolean quantityValid = false;
			
		try {
			
			System.out.printf("Enter quantity to %s: ", (buyOrSell == UPDATE_BUY) ? "buy" : "sell");
			quantity = scanner.nextInt();
			
			quantityValid = quantity > 0;				// quantity input must be positive and non-zero
			
			if (buyOrSell == UPDATE_SELL) quantity *= -1;	// negate quantity if selling
			
		} catch(InputMismatchException e) {
			
			scanner.nextLine();							// flush buffer on input mismatch
		}
		
		if (quantityValid == false) {					// unsuccessful if quantity is invalid
			
			System.out.println("Invalid quantity...");
			return false;
		}
		
		
		/* perform update and return result */
		return this.inventory.get(index).updateItem(quantity);
	}
	
	/**
	 * @return the string representation of the Inventory.
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder("Inventory:\n");
		
		for (int i = 0; i < this.inventory.size(); ++i)		// for each FoodItem in Inventory
			sb.append(inventory.get(i) + "\n");					// append the item
		return sb.toString();
	}
	
	/**
	 * Recursive binary search.
	 * Based on Lab3 submission.
	 * @param array - the array to search within 
	 * @param searchVal - the value to search for
	 * @param firstIndex - the first index of the remaining values (inclusive)
	 * @param lastIndex - the last index of the remaining values (inclusive)
	 * @return the index of the searchVal or -1.
	 */
	public int recursiveBinarySearch(ArrayList<FoodItem> array, FoodItem searchVal, int firstIndex, int lastIndex) {

		int middle = (lastIndex - firstIndex) / 2 + firstIndex;
		int index = -1;
		
		// early out if value is out of bounds 
		if (array.get(firstIndex).compareTo(searchVal) > 0
			|| array.get(lastIndex).compareTo(searchVal) < 0)
			return index;
		
		// check if value is at firstIndex
		else if (array.get(firstIndex).compareTo(searchVal) == 0) index = firstIndex;
		
		// check if value is at lastIndex
		else if (array.get(lastIndex).compareTo(searchVal) == 0) index = firstIndex;
		
		// check if there are only two elements remaining
		else if (middle == firstIndex || middle == lastIndex) return index;
		
		// check if value is at middle 
		else if (array.get(middle).compareTo(searchVal) == 0) index = firstIndex = lastIndex = middle;
		
		// otherwise, continue pruning
		
		// if searchVal is larger than middle value, prune lower half and recurse
		else if (array.get(middle).compareTo(searchVal) < 0)
			index = recursiveBinarySearch(array, searchVal, middle + 1, lastIndex);
		
		// if searchVal is smaller than middle value, prune upper half and recurse
		else index = recursiveBinarySearch(array, searchVal, firstIndex, middle - 1);
		
		return index;
	}
}


/**
 * Comparator for FoodItem.
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
class SortByItemCode implements Comparator<FoodItem> {

	@Override
	/**
	 * Compare FoodItems by FoodItem::compareTo().
	 * Null values are considered the greatest.
	 */
	public int compare(FoodItem o1, FoodItem o2) {
		
		return o1.compareTo(o2);
	}
}