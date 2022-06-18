import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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
		inventory.ensureCapacity(INV_SIZE);			// ensure minimum inventory size
	}
	
	
/* Methods */
	
	/**
	 * Adds an item to the Inventory array.
	 * O(n * log * n) time complexity.
	 * NOTE: not sorted if isKb == false
	 * @param scanner - user input stream
	 * @param isKb - whether the scanner is a keyboard (true) or file (false)
	 * @return true if program successfully reads in all data, otherwise returns false
	 */
	public boolean addItem(Scanner scanner, boolean isKb) {
		
		/* input item type and initialize toAdd */
		
		FoodItem toAdd = null;	// polymorphic item to add to Inventory; initialized based on user input
		
		do {
			
			/* prompt item type input */
			
			if (isKb) System.out.printf(
					"Do you wish to add a fruit(%s), vegetable(%s), preserve(%s) or meat(%s)? ",
					Fruit.itemType,
					Vegetable.itemType,
					Preserve.itemType,
					Meat.itemType);
			
			String choice = scanner.next();	// input choice
			
			
			/* add new item of type input */
			
			switch (choice) {
			case Fruit.itemType:				// add a Fruit product
				toAdd = new Fruit();
				break;
				
			case Vegetable.itemType:				// add a Vegetable product
				toAdd = new Vegetable();
				break;
			
			case Preserve.itemType:				// add a Preserve product
				toAdd = new Preserve();
				break;
				
			case Meat.itemType:				// add a Meat product
				toAdd = new Meat();
				break;
				
			default:					// input is invalid
				if (isKb) System.out.println("Invalid entry");
				break;
			}
			
			
		/* exit loop once toAdd has been initialized */
		} while(toAdd == null && isKb);
		
		
		/* input itemCode and check for duplicates */
		if (isKb) System.out.print("Enter the code for the item: ");
		boolean result = toAdd.inputCode(scanner, isKb);
		
		if (this.alreadyExists(toAdd) >= 0 || result == false) {	// unsuccessful if code already exists or if code input fails
			
			if (isKb) System.out.println("Item code already exists");
			return false;
		}
		
		
		/* initialize new item with input */
		result = toAdd.addItem(scanner, isKb);
		
		
		/* add item if input successful */
		if (result == true)	this.inventory.add(toAdd);
		
		
		/* sort by itemCode if user input */
		if (isKb) inventory.sort(new OrderFoodItemByItemCode());
		
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
		
		/* find item */
		
		FoodItem item = this.findItem(scanner);
		
		if (item == null) return false;	// early out if item is invalid (item code not found)
		
		
		/* input quantity */

		int quantity = -1;
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
		return item.updateItem(quantity);
	}
	
	public FoodItem findItem(Scanner scanner) {
		
		if (this.inventory.size() == 0) {
			System.out.println("Inventory empty.");
			return null;	// early out if inventory is empty
		}
		
		int index = -1;
		
		/* input item code */
		
		FoodItem searchItem = new FoodItem(null);
		
		System.out.print("Enter the code for the item: ");
		boolean codeValid = searchItem.inputCode(scanner, true);
		
		if (codeValid == false)	return null;			// unsuccessful if code is not entered
		
		
		index = this.alreadyExists(searchItem);
		
		if (index == -1) {
			
			System.out.println("Code not found in inventory...");
			return null;
		}
		
		return this.inventory.get(index);
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
	
	/**
	 * Load FoodItems from disk.
	 * @param scanner - user input stream
	 */
	public void load(Scanner scanner) {
		
		/* prompt path */
		
		System.out.print("Enter the filename to load: ");
		String path = scanner.nextLine();
		
		try {
			
			/* create file scanner */
			Scanner itemScanner = new Scanner(new File(path));
			
			
			/* load all FoodItems in file */
			
			boolean success = true;
			while (itemScanner.hasNext() && success == true) {
				
				success = this.addItem(itemScanner, false);
			}
			
			itemScanner.close();
			
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found...");
			
		} catch (NoSuchElementException e) {
			
			System.out.println("File format error...");
		}
		
		/* sort inventory */
		this.inventory.sort(new OrderFoodItemByItemCode());
	}
	
	/**
	 * Write FoodItems to file
	 * @param scanner - user input stream
	 */
	public void save(Scanner scanner) {
		
		/* prompt path */
		
		System.out.print("Enter the filename to save to: ");
		String path = scanner.nextLine();
		
		try {
			PrintWriter pw = new PrintWriter(path);
			
			for (FoodItem item : this.inventory)
				item.serialize(pw);
			
			pw.close();
			
		} catch (Exception e) {
			
			System.out.println("Cannot write to file specified...");
		}
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
class OrderFoodItemByItemCode implements Comparator<FoodItem> {

	@Override
	/**
	 * Compare FoodItems by FoodItem::compareTo().
	 * Null values are considered the greatest.
	 */
	public int compare(FoodItem o1, FoodItem o2) {
		
		return o1.compareTo(o2);
	}
}