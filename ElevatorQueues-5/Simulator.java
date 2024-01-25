/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 3
*/

class Simulator {
    // Simulate class takes in probability (between 0 and 1), number of floors (>1), number of elevators (>0), lenght of simulation (>0)
    public static void simulate(double probability, int floorCount, int elevatorCount, int simulationLength) {
        // Checks if conditions are met
        if (probability < 0.0 || probability > 1.0 || floorCount <= 1 || elevatorCount <= 0 || simulationLength <= 0) {
            throw new IllegalArgumentException("\nNO SIMULATION - invalid parameters, Try again");
        }

        // Simulation variables (totalwaittime and totalrequests are updates through each run)
        RequestQueue requestQueue = new RequestQueue();
        Elevator[] elevators = new Elevator[elevatorCount];
        // totalWaitTime and RequestCount are only updated when the elevator reaches the source floor
        int totalWaitTime = 0;
        int requestCount = 0;

         // adds the number of elevators
        for (int i = 0; i < elevatorCount; i++) {
            elevators[i] = new Elevator();
        }

        // Loops through each unit of time
        for (int currentTime = 1; currentTime <= simulationLength; currentTime++) {
            // Check if there is an elevator request if the math.random is less than inputted probability
            if (new BooleanSource(probability).requestArrived()) {
                // Adds a new request to the queue
                Request newRequest = new Request(floorCount);
                newRequest.setTimeEntered(currentTime);
                requestQueue.enqueue(newRequest);
            }

            // Case 1: Assigns requests to idle elevators (use an empty elevator)
            for (Elevator elevator : elevators) {
                // If the elevator state is Idle
                if (elevator.getElevatorState() == Elevator.IDLE && !requestQueue.isEmpty()) {
                    // Dequeues from requestQueue and sets the request to the elevator
                    Request r = requestQueue.dequeue();
                    elevator.setRequest(r);
                    // Checks the current floor to the sourcefloor to determine elevator state
                    if (!(elevator.getCurrentFloor() == r.getSourceFloor())) {
                        elevator.setElevatorState(Elevator.TO_SOURCE);
                    } else {
                        // Adds to the requestCount if the elevators current floor is equal to the source floor
                        requestCount++;
                        // totalWaitTime equals the (currenttime - requests timeentered) --> Should be 0
                        totalWaitTime += (currentTime - elevator.getRequest().getTimeEntered());
                        elevator.setElevatorState(Elevator.TO_DESTINATION);
                    }
                }

                // Case 2: If the elvator state is TO_SOURCE (pick up a passenger)
                if (elevator.getElevatorState() == Elevator.TO_SOURCE) {
                    // If the elevator's current floor equals the requests sourcefloor, then change state to TO_DESTINATION
                    if (elevator.getCurrentFloor() == elevator.getRequest().getSourceFloor()) {
                        elevator.setElevatorState(Elevator.TO_DESTINATION);
                        // Adds to the requestCount since elevator reached passenger
                        requestCount++;
                        // totalWaitTime equals the (currenttime - requests timeentered) - 1
                        totalWaitTime += (currentTime - elevator.getRequest().getTimeEntered() - 1);
                    // Subtract or add elevator's current floor if current floor doesnot equal source floor
                    } else if (elevator.getCurrentFloor() > elevator.getRequest().getSourceFloor()) {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    } else {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                    }
                
                // Case 3: Repeat the above but if the elevator state is TO_DESTINGATION (deliver a passenger)
                } else if (elevator.getElevatorState() == Elevator.TO_DESTINATION) {
                    if (elevator.getCurrentFloor() == elevator.getRequest().getDestinationFloor()) {
                        // Set the elevator to idle after delivering passenger
                        elevator.setElevatorState(Elevator.IDLE);
                        //totalWaitTime += (currentTime - elevator.getRequest().getTimeEntered());
                    } else if (elevator.getCurrentFloor() > elevator.getRequest().getDestinationFloor()) {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    } else {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                    }
                }
            }
        }
        
        // Calculate the average by dividing total wait time and total requests
        double averageWaitTime = (double) totalWaitTime / requestCount;

        // Output results
        System.out.println("");
        System.out.println("Total Wait Time: " + totalWaitTime);
        System.out.println("Total Requests: " + requestCount);
        System.out.println("Average Wait Time: " + String.format("%.2f", averageWaitTime));
    }
}
