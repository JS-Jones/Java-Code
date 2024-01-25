/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 3
*/

import java.util.Scanner;

public class Analyzer {
    // Main Method takes in scanner and propts user for inputs for probabiliyt, floor count, elevator count, and simulation lenght
    public static void main(String[] args) {
        /**
         * Note: Nothing in the instructions indicated that the program should loop, so this only runs 1 simulation
         * There is a single Try/Catch IllegealArgumentException to check the values of the initial parameters and loops until correct values are given
        */
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Try/Catch to find any errors with the inputted data
            try {
                System.out.println("Welcome to the Elevator simulator!");
                System.out.print("\nPlease enter the probability of arrival for Requests: ");
                double probability = scanner.nextDouble();
                System.out.print("Please enter the number of floors: ");
                int floorCount = scanner.nextInt();
                System.out.print("Please enter the number of elevators: ");
                int elevatorCount = scanner.nextInt();
                System.out.print("Please enter the length of the simulation (in time units): ");
                int simulationLength = scanner.nextInt();

                // Run the simulation using the above inputs
                Simulator.simulate(probability, floorCount, elevatorCount, simulationLength);

                scanner.close();
                return;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
}