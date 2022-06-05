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
	 * The name of the orchard of origin.
	 */
	private String orchardName;
	
	
/* Constructors */
	
	/**
	 * Default constructor.
	 */
	public Fruit() {
		// TODO: implement Fruit::Fruit()
		
	}
	
	
/* Methods */
	
	/**
	 * Reads from the Scanner object passed in and fills the data member fields of the class with valid data
	 * @param scanner - user input stream
	 * @return true if program successfully reads in all fields, otherwise returns false
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		// TODO: implement Fruit::addItem()
		
		return false;	// placeholder
	}
	
	/**
	 * @return all the data members in the class in String format. 
	 */
	@Override
	public String toString() {
		// TODO: implement Fruit::toString()
		
		return "";	// placeholder
	}
}
