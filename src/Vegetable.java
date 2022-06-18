import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A Vegetable product that can be stored in an Inventory.
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class Vegetable extends FoodItem {

/* Member Variables */
	
	/**
	 * The type of item as a String.
	 */
	protected static final String itemType = "v";
	
	/**
	 * The name of the farm of origin.
	 */
	private String farmName;
	
	
/* Constructors */
	
	/**
	 * Default constructor.
	 */
	public Vegetable() {
		super(itemType);
		
		this.farmName = "Not set";
	}
	
	
/* Methods */
	
	/**
	 * Reads from the Scanner object passed in and fills the data member fields of the class with valid data
	 * @param scanner - user input stream
	 * @param isKb - whether the scanner is a keyboard (true) or file (false)
	 * @return true if program successfully reads in all fields, otherwise returns false
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean isKb) {
		
		boolean result = super.addItem(scanner, isKb);	// input fields in base class
		
		if (result == false)	return false;		// early out if base returns false
		
		if (isKb) System.out.print("Enter the name of the farm supplier: ");
		this.farmName = scanner.nextLine();			// input farm name
		
		return true;
	}
	
	/**
	 * @return all the data members in the class in String format. 
	 */
	@Override
	public String toString() {
		
		return String.format(
				"%s farm supplier: %s",
				super.toString(),
				this.farmName);
	}
	
	/**
	 * Output all data members in to file.
	 * @param pw - file stream
	 */
	@Override
	public void serialize(PrintWriter pw) {
		super.serialize(pw);
		
		pw.println(this.farmName);
	}
}
