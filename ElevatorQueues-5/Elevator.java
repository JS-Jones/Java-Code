/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 3
*/

public class Elevator {
    private int currentFloor;
    private int elevatorState;
    private Request request;

    // Sets final value for IDLE, TO_SOURCE (pick up person), and TO_DESTINATION (drop off person) to distinct values
    public static final int IDLE = 0;
    public static final int TO_SOURCE = 1;
    public static final int TO_DESTINATION = 2;

    // Constructor - sets the request to null, elevatorState to IDLE, and currentFloor to 1
    public Elevator() {
        currentFloor = 1;
        elevatorState = IDLE;
        request = null;
    }

    // Returns the currentFloor
    public int getCurrentFloor() {
        return currentFloor;
    }

    // sets the currentFloor
    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    // Returns the elevatorState
    public int getElevatorState() {
        return elevatorState;
    }

    // sets the elevatorState
    public void setElevatorState(int state) {
        this.elevatorState = state;
    }

    // returns the request
    public Request getRequest() {
        return request;
    }

    // Sets the value of request
    public void setRequest(Request state) {
        this.request = state;
    }

}
