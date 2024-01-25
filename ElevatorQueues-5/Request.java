/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 3
*/

import java.lang.Math;

public class Request {
    private int sourceFloor;
    private int destinationFloor;
    private int timeEntered;

    // Constructor
    public Request(int numFloors) {
        // The random values must be between 1 and the number of floors in the building, so add 1 to math.random
        sourceFloor = (int)(Math.random()*numFloors) + 1;
        destinationFloor = (int)(Math.random()*numFloors) + 1;
    }

    // Returns/Getter method for sourceFloor
    public int getSourceFloor() {
        return sourceFloor;
    }

    // Returns/Getter method for destinationFloor
    public int getDestinationFloor() {
        return destinationFloor;
    }

    // Returns/Getter method for timeEntered
    public int getTimeEntered() {
        return timeEntered;
    }

    // Setter method for timeEntered
    public void setTimeEntered(int time) {
        this.timeEntered = time;
    }
}
