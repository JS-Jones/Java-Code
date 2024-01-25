/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 2
*/

public class ItemInfo {
    private String rfidTagNumber;
    private String originalLocation;
    private String currentLocation;
    private String name;
    private double price;

    // Constructor for ItemInfo
    public ItemInfo(String rfidTagNumber, String originalLocation, double price, String name) {
        this.setName(name);
        this.setPrice(price);
        this.setRfidTagNumber(rfidTagNumber);
        this.setOriginalLocation(originalLocation);
        this.setCurrentLocation(originalLocation); // Set Current location to starting postion/Original location (on Shelf)
    }

    // Sets the Name of the product
    public void setName(String name) {
        // The length of the name field has a maximum of 20 characters otherwise throw exception.
        if (name.length() > 20) {
            throw new IllegalArgumentException("Name exceeds 20 characters.");
        }
        this.name = name;
    }

    // Returns the Name of the product
    public String getName() {
        return name;
    }

    // Sets the Price of the product
    public void setPrice(double price) {
        // Price must be positive otherwise throw exception
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }
        this.price = price;
    }

    // Returns the Price of the product
    public double getPrice() {
        return price;
    }

    // Sets the rfidTagNumber of the product
    public void setRfidTagNumber(String rfidTagNumber) {
       int n = rfidTagNumber.length();
       
        // Checks if the lenght of the tag is 9 characters
        if (n != 9){
            throw new IllegalArgumentException("Invalid RFID Tag Number length.");
        }
        
        // Loop through all the characters of the tag and check if it is not between 0-9 and A-F
        for (int i = 0; i < n; i++) {
            char ch = Character.toUpperCase(rfidTagNumber.charAt(i));
            if ((ch >= 'A' && ch <= 'F') || (ch >= '0' && ch <= '9')){
                continue;
            } else {
                throw new IllegalArgumentException("Invalid RFID Tag Number format.");
            }
        }
        this.rfidTagNumber = rfidTagNumber.toUpperCase();
    }

    // Returns the rfidTagNumber
    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    // Set the Original location of the product
    public void setOriginalLocation(String originalLocation) {
        int n = originalLocation.length();

        // throws exception if the length of the string is  not 6 characters and if the starting character is  not 's'
        if ((originalLocation.charAt(0) != 's' && originalLocation.charAt(0) != 'S') || (n != 6)){
            throw new IllegalArgumentException("Invalid original location length or starting character.");
        }

        // Checks if the last 5 characters are strings
        for (int i = 1; i < n; i++) {
            char ch = originalLocation.charAt(i);
            if (ch >= '0' && ch <= '9'){
                continue;
            } else {
                throw new IllegalArgumentException("Invalid original location format.");
            }
        }

        this.originalLocation = originalLocation.toLowerCase();
    }

    // returns the original location of the product
    public String getOriginalLocation() {
        return originalLocation;
    }

    // returns the Current location of the product
    public String getCurrentLocation() {
        return currentLocation;
    }

    // sets the current location of the product
    public void setCurrentLocation(String currentLocation) {
        int n = currentLocation.length();

        if (currentLocation.equalsIgnoreCase("out")) {
            // Case where product is checked out
            this.currentLocation = currentLocation.toLowerCase();

        } else if ((n == 6) && (currentLocation.charAt(0) == 'S' || currentLocation.charAt(0) == 's')) {
            // Case where product is still on shelf
            for (int i = 1; i < n; i++) {
                char ch = currentLocation.charAt(i);
                if (ch >= '0' && ch <= '9'){
                    continue;
                } else {
                    throw new IllegalArgumentException("Invalid current location format.");
                }
            }
            this.currentLocation = currentLocation.toLowerCase();

        } else if ((n == 4) && (currentLocation.charAt(0) == 'C' || currentLocation.charAt(0) == 'c')) {
            // Case where product is in a cart
            for (int i = 1; i < n; i++) {
                char ch = currentLocation.charAt(i);
                if (ch >= '0' && ch <= '9'){
                    continue;
                } else {
                    throw new IllegalArgumentException("Invalid current location format.");
                }
            }
            this.currentLocation = currentLocation.toLowerCase();

        } else {
            // Throws exception for all other cases
            throw new IllegalArgumentException("Invalid current location format.");
        }
    }
}
