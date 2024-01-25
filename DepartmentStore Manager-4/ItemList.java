/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 2
*/


public class ItemList {
    private ItemInfoNode head;
    private ItemInfoNode tail;

    // Constructor for ItemList, head contains link to first node, tail contains link to last node
    public ItemList() {
        this.head = null;
        this.tail = null;
    }

    // Inserts the info into the list in its correct position based on its rfidTagNumber
    public void insertInfo(String name, String rfidTag, double price, String initPosition) {
        // Order of Complexity: O(N) - In the worst case, you need to traverse the whole list to insert a item with it still being sorted
        // Create new item and sent its Node info to it
        ItemInfo newItem = new ItemInfo(rfidTag, initPosition, price, name);
        ItemInfoNode newNode = new ItemInfoNode();
        newNode.setInfo(newItem);
        // Case where head and tail is empty
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            // Set Cursor to head and loop until current is null
            ItemInfoNode current = head;
            while (current != null) {
                // hex-digit order in the ASCII character set, so compareTo works, will be greater than 0 if rfidtag is less than current
                if (current.getInfo().getRfidTagNumber().compareTo(rfidTag) >= 0) {
                    // if current is head, the newNode's previous will be null
                    if (current == head) {
                        newNode.setNext(head);
                        head.setPrev(newNode);
                        head = newNode;
                    } else {
                        newNode.setNext(current);
                        newNode.setPrev(current.getPrev());
                        current.getPrev().setNext(newNode);
                        current.setPrev(newNode);
                    }
                    return;
                }
                current = current.getNext();
            }
            // If the newNode is greater than all existing nodes, add it to the end of the list
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
    }

    //Removes all nodes in the list that have current location listed as "out" and displays a list of all items that have been removed
    public void removeAllPurchased() {
        // Order of Complexity: O(N) - While loops shows how you need look through the whole list to find matches to remove
        System.out.println("\nThe following item(s) have removed from the system:\n");
        printHeading();
        ItemInfoNode current = head;
        while (current != null) {
            // Loops through the list of products until cursor equals null
            if (current.getInfo().getCurrentLocation().equalsIgnoreCase("out")) {
                // Resets the head if current is head
                if (current == head) {
                    head = current.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    } else {
                        tail = null;
                    }
                // Resets the tail if current is tail
                } else if (current == tail) {
                    tail = current.getPrev();
                    if (tail != null) {
                        tail.setNext(null);
                    } else {
                        head = null;
                    }
                // All other cases of current
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                printItemInfo(current.getInfo());
                //System.out.println("Removed: " + current.getInfo().getName() + " (" + current.getInfo().getRfidTagNumber() + ")");
            }
            current = current.getNext();
        }
        System.out.println();
    }

    // Moves an item with a given rfidTagNumber from a source location to a dest location
    public boolean moveItem(String rfidTag, String source, String dest) {
        // Order of Complexity: O(N) - While loop search the whole list for corresponding rfidtag to move
        ItemInfoNode current = head;
        while (current != null) {
            // Loops through the linked list and finds a product with the same rfidtag and currentlocation
            if (current.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTag) && current.getInfo().getCurrentLocation().equalsIgnoreCase(source)) {
                // Covers the case where a product has the out and but is still recorded or if formatting is wrong
                if (dest.equalsIgnoreCase("out") || !ValidFormat(dest)) {
                    throw new IllegalArgumentException("Invalid destination location format.");
                }
                // Changes the current location of the product to the new location
                current.getInfo().setCurrentLocation(dest);
                //System.out.println("Item moved: " + current.getInfo().getName() + " (" + current.getInfo().getRfidTagNumber() + ")");
                return true;
            }
            current = current.getNext();
        }
        // Return false if no item found
        System.out.println("Item not found at the specified source location.");
        return false;
    }

    // Method to check whether input data follows correct formatiing and returns boolean
    private boolean ValidFormat(String location) {
        int n = location.length();

        if ((n == 6) && (location.charAt(0) == 'S' || location.charAt(0) == 's')) {
            // Case where product is still on shelf
            for (int i = 1; i < n; i++) {
                char ch = location.charAt(i);
                if (ch >= '0' && ch <= '9'){
                    continue;
                } else {
                    return false;
                }
            }
            return true;

        } else if ((n == 4) && (location.charAt(0) == 'C' || location.charAt(0) == 'c')) {
            // Case where product is in a cart
            for (int i = 1; i < n; i++) {
                char ch = location.charAt(i);
                if (ch >= '0' && ch <= '9'){
                    continue;
                } else {
                    return false;
                }
            }
            return true;

        } else {
            // Return false for all other cases
            return false;
        }
    }

    // Print all nodes in the linked list until current = null
    public void printAll() {
        // Order of Complexity: O(N) - There is a while loop that goes through every instance in the list to print
        printHeading();
        ItemInfoNode current = head;
        while (current != null) {
            printItemInfo(current.getInfo());
            current = current.getNext();
        }
        System.out.println();
    }

    // Print nodes in the linked list that are in a specific location until current = null
    public void printByLocation(String location) {
        // Order of Complexity: O(N) - There is a while loop that goes through every instance in the list to print if it matches the location condition (doesnt affted overall Big O)
        printHeading();
        ItemInfoNode current = head;
        while (current != null) {
            if (current.getInfo().getCurrentLocation().equalsIgnoreCase(location)) {
                printItemInfo(current.getInfo());
            }
            current = current.getNext();
        }
        System.out.println();
    }

    // Print nodes in the linked list that have a specific RFID
    public void printByRfidTagNumber(String RFID) {
        System.out.println();
        printHeading();
        ItemInfoNode current = head;
        while (current != null) {
            if (current.getInfo().getRfidTagNumber().equalsIgnoreCase(RFID)) {
                printItemInfo(current.getInfo());
            }
            current = current.getNext();
        }
        System.out.println();
    }

    // Method to just print out the start of every table
    private void printHeading(){
        // Formatting instructions were not clear, so I'm just using whatever looks right
        System.out.println(String.format("%-19s%-19s%-19s%-19s%-6s", "Item Name", "RFID", "Original Location", "Current Location", "Price"));
        System.out.println(String.format("%-19s%-19s%-19s%-19s%-6s", "---------", "---------", "---------", "---------", "---------"));
    }

    // Method that prints each item formatted 
    private void printItemInfo(ItemInfo item) {
        String rfidTag = item.getRfidTagNumber();
        String originalLocation = item.getOriginalLocation();
        String currentLocation = item.getCurrentLocation();
        String name = item.getName();
        double price = item.getPrice();
        System.out.println(String.format("%-19s%-19s%-19s%-19s%-6.2f", name, rfidTag, originalLocation, currentLocation, price));
    }

    // Moves items out of place back to their original location by comparing current location with Original location
    public void cleanStore() {
        // Order of Complexity: O(N) - Traverses the whole list (while loop) to correct each incorrect position
        System.out.println("\nThe following item(s) have been moved back to their original locations:\n");
        printHeading();
        ItemInfoNode current = head;
        while (current != null) {
            if ((current.getInfo().getCurrentLocation().startsWith("s") || current.getInfo().getCurrentLocation().startsWith("S")) && !current.getInfo().getCurrentLocation().equals(current.getInfo().getOriginalLocation())) {
                printItemInfo(current.getInfo());
                current.getInfo().setCurrentLocation(current.getInfo().getOriginalLocation());
            }
            current = current.getNext();
        }
        System.out.println();
    }

    // Checks out all the items in a cart (changes location to out) and returns the total cost
    public double checkOut(String cartNumber) {
        // Order of Complexity: O(N) - Traverses the whole list (while loop) to search of current locations of the inputted cart Number
        System.out.println();
        printHeading();
        ItemInfoNode current = head;
        double totalCost = 0.00;
        while (current != null) {
            // Checks for a specific cart number and adds to the 'totalCost' variable
            if (current.getInfo().getCurrentLocation().equalsIgnoreCase(cartNumber)) {
                current.getInfo().setCurrentLocation("out");
                totalCost += current.getInfo().getPrice();
                printItemInfo(current.getInfo());
            }
            current = current.getNext();
        }
        System.out.println("\nThe total cost for all merchandise in " + cartNumber + " was $" + String.format("%.2f", totalCost));
        System.out.println();
        return totalCost;
    }

}
