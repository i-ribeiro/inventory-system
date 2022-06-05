import java.util.Scanner;

/**
 * A Meat product that can be stored in an Inventory.
 * The 4th subclass of FoodItem required by the assignment.
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class Meat extends FoodItem {
	
/* Member Variables */
	
	/**
	 * The name of the farm of origin.
	 */
	private String farmName;
	
	
/* Constructors */
	
	/**
	 * Default constructor.
	 */
	public Meat() {

		this.farmName = "Not set";	// initialize with invalid value so it's obvious if addItem() is not called
	}
	
	
/* Methods */
	
	/**
	 * Reads from the Scanner object passed in and fills the data member fields of the class with valid data
	 * @param scanner - user input stream
	 * @return true if program successfully reads in all fields, otherwise returns false
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		// TODO: implement Meat::addItem()
		
		return false;	// placeholder
	}
	
	/**
	 * @return all the data members in the class in String format. 
	 */
	@Override
	public String toString() {
		// TODO: implement Meat::toString()
		
		return "";	// placeholder
	}
}
