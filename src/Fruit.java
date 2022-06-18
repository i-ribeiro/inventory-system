import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A Fruit product that can be stored in an Inventory.
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class Fruit extends FoodItem {

/* Member Variables */
	
	/**
	 * The type of item as a String.
	 */
	protected static final String itemType = "f";
	
	/**
	 * The name of the orchard of origin.
	 */
	private String orchardName;
	
	
/* Constructors */
	
	/**
	 * Default constructor.
	 */
	public Fruit() {
		super(itemType);
		
		this.orchardName = "Not set";	// initialize with invalid value so it's obvious if addItem is not called
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
		boolean result = super.addItem(scanner, isKb);	// input base class fields
		if (result == false)	return false;		// early out if base returns false
		
		if (isKb) System.out.print("Enter the name of the orchard supplier: ");
		this.orchardName = scanner.nextLine();
		
		return true;
	}
	
	/**
	 * @return all the data members in the class in String format. 
	 */
	@Override
	public String toString() {
		
		return String.format(
				"%s orchard supplier: %s",
				super.toString(),
				this.orchardName);
	}
	
	/**
	 * Output all data members in to file.
	 * @param pw - file stream
	 */
	@Override
	public void serialize(PrintWriter pw) {
		super.serialize(pw);
		
		pw.println(this.orchardName);
	}
}
