import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A Preserve product that can be stored in an Inventory.
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class Preserve extends FoodItem {

/* Member Variables */
	
	/**
	 * The type of item as a String.
	 */
	protected static final String itemType = "p";
	
	/**
	 * The size of the preserve jar in millimetres.
	 */
	private int jarSize;
	
	
/* Constructors */
	
	/**
	 * Default constructor.
	 */
	public Preserve() {
		super(itemType);
		
		this.jarSize = -1;	// initialize with invalid value in case addItem() is not called
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
		if (result == false) return false; 			// early out if base returns false
		
		int size = -1;
		boolean sizeValid = false;
		
		
		/* input jar size */
		
		do {
			try {
				
				if (isKb) System.out.print("Enter the size of the jar in millimetres: ");
				size = scanner.nextInt();
				sizeValid = size > 0;
				if (sizeValid == false && isKb)	System.out.println("Size must be greater than zero.");
				
			} catch (InputMismatchException e) {
				
				if (isKb) System.out.println("Invalid entry");
				
			} finally {
				
				scanner.nextLine(); // flush buffer
			}
			
		} while (sizeValid == false && isKb);
		
		
		/* set jar size */
		this.jarSize = size;
		
		return sizeValid;
	}
	
	/**
	 * @return all the data members in the class in String format. 
	 */
	@Override
	public String toString() {
		
		return String.format(
				"%s size: %d mL",
				super.toString(),
				this.jarSize);
	}
	
	/**
	 * Output all data members in to file.
	 * @param pw - file stream
	 */
	@Override
	public void serialize(PrintWriter pw) {
		super.serialize(pw);
		
		pw.println(this.jarSize);
	}
}
