import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class to represent a food product that can be stored in an Inventory. 
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class FoodItem implements Comparable<FoodItem> {
	
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
	
	/**
	 * Item subtype string representation.
	 */
	public final String itemType; 
	
	
/* Constructors */
	
	/**
	 * @param itemType - the type of item this is
	 */
	public FoodItem(String itemType) {
		
		this.itemCode = -1;
		this.itemName = "Invalid item";
		this.itemType = itemType;		// item type set by subclasses via constructor chaining
	}
	
	
/* Methods */
	
	/**
	 * Reads from the Scanner object passed in and fills the data member fields of the class with valid data
	 * @param scanner - user input stream
	 * @param isKb - whether the scanner is a keyboard (true) or file (false)
	 * @return true if program successfully reads in all fields, otherwise returns false
	 */
	public boolean addItem(Scanner scanner, boolean isKb) {
		
		String name = "Not set";
		int quantity = -1;
		float cost = 0.f, price = 0.f;
		
		boolean nameValid = false;
		boolean quantityValid = false;
		boolean costValid = false;
		boolean priceValid = false;
		boolean inputValid = false;
		
		
		/* input fields */
		
		do {
			try {
				
				/* input name */
				if (nameValid == false) {		// if name has not already been set
					if (isKb) System.out.print("Enter the name for the item: ");
					name = scanner.nextLine();
					nameValid = true;
				}
				
				/* input quantity */
				if (quantityValid == false) {	// if quantity has not already been set
					if (isKb) System.out.print("Enter the quantity for the item: ");
					quantity = scanner.nextInt();
					quantityValid = quantity > 0;	// quantity must be positive, non-zero
					
					if (quantityValid == false) {	// if quantity is still not valid, restart from top
						
						if (isKb) System.out.println("Quantity must be a positive integer.");
						continue;
					}
					
					scanner.nextLine();	// flush buffer
				}
				
				/* input cost */
				if (costValid == false) {		// if cost has not already been set
					if (isKb) System.out.print("Enter the cost of the item: ");
					cost = scanner.nextFloat();
					costValid = cost > 0.f;			// cost must be positive, non-zero
					
					if (costValid == false) {		// if cost is still not valid, restart from top
						
						if (isKb) System.out.println("Cost must be a positive value.");
						continue;
					}
					
					scanner.nextLine();	// flush buffer
				}
				
				/* input price */
				if (priceValid == false) {		// if price has not already been set
					if (isKb) System.out.print("Enter the sales price of the item: ");
					price = scanner.nextFloat();
					priceValid = price > 0.f;		// price must be positive, non-zero
					
					if (priceValid == false) {		// if price is still not valid, restart from top
						
						if (isKb) System.out.println("Price must be a positive value.");
						continue;
					}
					
					scanner.nextLine();	// flush buffer
				}
				
			} catch (InputMismatchException e) {	// warn on input mismatch
				
				if (isKb) System.out.println("Invalid entry");
				scanner.nextLine();		// flush buffer
			}
			
			
			inputValid = // input is valid if all fields are valid
					nameValid
					&& quantityValid
					&& costValid
					&& priceValid;
			
		} while (inputValid == false && isKb);	// loop until all fields are valid
		
		
		/* set fields */
		
		this.itemName = name;
		this.itemQuantityInStock = quantity;
		this.itemCost = cost;
		this.itemPrice = price;
		
		return inputValid;
	}
	
	/**
	 * Reads a valid itemCode from the Scanner object.
	 * @param scanner - user input stream
	 * @param isKb - whether the scanner is a keyboard (true) or file (false)
	 * @return true/false if successful
	 */
	public boolean inputCode(Scanner scanner, boolean isKb) {
		
		boolean result = true;
		int code = 0;
		
		
		/* input code */
		
		try {
			
			code = scanner.nextInt();	// input code
			
		} catch (InputMismatchException e) {
			
			if (isKb) System.out.println("Invalid code");
			result = false;				// invalidate input on input mismatch
			
		} finally {
			
			scanner.nextLine();			// flush buffer
		}
		
		
		/* set itemCode if valid */
		if (result)	this.itemCode = code;
		
		
		return result;
	}
	
	/**
	 * @param item - the item to compare
	 * @return true if the itemCode of the object being acted on and the item object parameter are the same value
	 */
	public boolean isEqual(FoodItem item) {
		
		return this.itemCode == item.itemCode;
	}
	
	/**
	 * Updates the quantity field by amount (positive or negative)
	 * @param amount - the updated amount
	 * @return true if successful, otherwise returns false
	 */
	public boolean updateItem(int amount) {
		
		/* check if quantity will be negative after update */
		boolean result = (this.itemQuantityInStock + amount) >= 0;
		
		/* if quantity is valid, update */
		if (result == true)	this.itemQuantityInStock += amount;
		
		/* return result */
		return result;
	}
	
	/**
	 * @return all the data members in the class in String format. 
	 */
	@Override
	public String toString() {
		
		return String.format(
				"Item: %d %s %d price: %.2f cost: %.2f", 
				this.itemCode, 
				this.itemName, 
				this.itemQuantityInStock, 
				this.itemPrice, 
				this.itemCost);
	}


	/**
	 * @param o - the other FoodItem to compare with
	 * @return the comparison between this FoodItem and o
	 */
	@Override
	public int compareTo(FoodItem o) {

		return this.itemCode - o.itemCode;
	}


	/**
	 * Output all data members in to file.
	 * @param pw - file stream
	 */
	public void serialize(PrintWriter pw) {
		
		pw.println(this.itemType);
		pw.println(this.itemCode);
		pw.println(this.itemName);
		pw.println(this.itemQuantityInStock);
		pw.println(this.itemPrice);
		pw.println(this.itemCost);
	}
}
