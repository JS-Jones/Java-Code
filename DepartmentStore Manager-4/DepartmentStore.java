/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 2
*/

import java.util.Scanner;

public class DepartmentStore {
    private ItemList itemList;
    private Scanner scanner;

    // Constructor
    public DepartmentStore() {
        this.itemList = new ItemList();
        this.scanner = new Scanner(System.in);
    }

    // Shows the menu and make selection
    public void displayMenu() {
        System.out.println("Welcome!\n");
        // Loops until Q is entered
        while (true) {
            System.out.println("C - Clean store");
            System.out.println("I - Insert an item into the list");
            System.out.println("L - List by location");
            System.out.println("M - Move an item in the store");
            System.out.println("O - Checkout");
            System.out.println("P - Print all items in store");
            System.out.println("R - Print by RFID tag number");
            System.out.println("U - Update inventory system");
            System.out.println("Q - Exit the program.\n");

            System.out.print("Please select an option: ");
            // Ignores casing of option selection
            String input = scanner.nextLine().toUpperCase();


            if (input.equals("C")){
                // Clean Store - Try/Catch to handle any errors during input
                itemList.cleanStore();
            } else if (input.equals("I")){
                try{
                    insertItem();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                }
            } else if (input.equals("L")){
                // List by Location
                listByLocation();
            } else if (input.equals("M")){
                // Move an item in the store - Try/Catch to handle any errors during input
                try{
                    moveItem();
                    System.out.println();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                }
            } else if (input.equals("O")){
                // Checkout
                checkOut();
            } else if (input.equals("P")){
                // Print all items in store
                System.out.println();
                itemList.printAll();
            } else if (input.equals("R")){
                // Print by RFID tag number
                printByRfidTagNumber();
            } else if (input.equals("U")){
                // Update inventory system
                updateInventorySystem();
            } else if (input.equals("Q")){
                // Quit the program
                System.out.println("\nGoodbye!");
                scanner.close();
                return;
            } else {
                // Handles all other cases
                System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

    // Prompts user for info to be inserted into the itemlist
    private void insertItem() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the RFID: ");
        String rfidTag = scanner.nextLine();

        System.out.print("Enter the original location: ");
        String originalLocation = scanner.nextLine();

        System.out.print("Enter the price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        itemList.insertInfo(name, rfidTag, price, originalLocation);

        System.out.println();
    }

    // Prompts user for a location and prints a table with items with that current location
    private void listByLocation() {
        System.out.print("Enter the location: ");
        String location = scanner.nextLine();
        itemList.printByLocation(location);
    }

    // Moves an item from its current position
    private void moveItem() {
        System.out.print("Enter the RFID: ");
        String rfidTag = scanner.nextLine();

        System.out.print("Enter the original location: ");
        String sourceLocation = scanner.nextLine();

        System.out.print("Enter the new location: ");
        String destLocation = scanner.nextLine();

        itemList.moveItem(rfidTag, sourceLocation, destLocation);
    }

    // Prompts user for a cart number and then checks the item out and prints the total cost
    private void checkOut() {
        System.out.print("Enter the cart number: ");
        String cartNumber = scanner.nextLine();
        itemList.checkOut(cartNumber);
    }

    // Print items based on RFID tag entered
    private void printByRfidTagNumber() {
        System.out.print("Enter the RFID tag number: ");
        String rfidTag = scanner.nextLine();
        itemList.printByRfidTagNumber(rfidTag);
    }

    // Updates the inventory by removing all items with a current position of "Out"
    private void updateInventorySystem() {
        itemList.removeAllPurchased();
    }

    public static void main(String[] args) {
        // Creates an new instance of department store and displays the menu
        DepartmentStore store = new DepartmentStore();
        store.displayMenu();
    }
}

