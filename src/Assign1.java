import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Tests the Inventory class.
 * @author Isaac Ribeiro, James Mwangi
 * Student Number: 040957075
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * CET-CS-Level 3
 */
public class Assign1 {
	
/* Menu Options */
	
	/**
	 * The menu option to add a FoodItem to Inventory.
	 */
	private static final int MENU_ADD		= 1;
	
	/** 
	 * The menu option to display all FoodItems in Inventory.
	 */
	private static final int MENU_DISPLAY 	= 2;
	
	/**
	 * The menu option to buy a FoodItem.
	 */
	private static final int MENU_BUY		= 3;
	
	/**
	 * The menu option to sell a FoodItem.
	 */
	private static final int MENU_SELL		= 4;
	
	/**
	 * The menu option to exit.
	 */
	private static final int MENU_EXIT		= 5;
	

	/**
	 * The entry point to test the Inventory class.
	 * @param args - not used
	 */
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		int choice = -1;
		
		Inventory inv = new Inventory();	// the Inventory object to test
		
		do {
			
			displayMenu();
			choice = inputMenuChoice(keyboard);
			
			switch (choice) {
			
			case MENU_EXIT:	break;	// handled by outer loop; do not modify
			
			case MENU_ADD:			// add a FoodItem to Inventory
				inv.addItem(keyboard);
				break;
				
			case MENU_DISPLAY:		// display Inventory
				System.out.println(inv);
				break;
				
			case MENU_BUY:			// buy a FoodItem
				// TODO: integrate Inventory::updateQuantity() - buy
				System.out.println("MENU_BUY placeholder");
				break;
				
			case MENU_SELL:			// sell a FoodItem
				// TODO: integrate Inventory::updateQuantity() - sell
				System.out.println("MENU_SELL placeholder");
				break;
				
			default:				// invalid choice
				System.out.println("...Invalid input, please try again...");
				break;
			}
			
		} while(choice != MENU_EXIT);	// stop when user chooses exit
		
		System.out.println("Exiting...");
		keyboard.close();
	}
	
	/**
	 * Displays the main menu options.
	 */
	public static void displayMenu() {

		System.out.print(
				"Please select one of the following: \n"
				+ "1: Add Item to Inventory \n"
				+ "2: Display Current Inventory \n"
				+ "3: Buy Item(s) \n"
				+ "4: Sell Item(s) \n"
				+ "5: To Exit \n"
				+ "> ");
	}
	
	/**
	 * Method to prompt the user to input a menu option selection.
	 * @param input - user input stream
	 * @return the menu option selected by the user.
	 */
	private static int inputMenuChoice(Scanner input) {

		int menuChoice = -1;
		
		// accept an integer value
		
		try {
			
			menuChoice = input.nextInt();
			
		} catch (InputMismatchException e) {
			
			input.next();
		}
		
		if (input.hasNextLine()) input.nextLine();
		
		return menuChoice;
	}
}
