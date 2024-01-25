/* Name: Joshua Jones
 * Email: jjo108@u.rochester.edu
 * Last modified: Mar 2 2023
 * Assignment: CSC 171 HW 4 - Determines best deals for contracts based on airplane input data
 */

import java.util.Scanner;

public class Airplane {
    // Private instance variable
    private String name;
    private float TOW_min;
    private float TOW_max;
    private float range;
    private float speed;
    private float hourly_cost;
    private float fuel_burn;

    // Constructor Method
    public Airplane(String name, float TOW_min, float TOW_max, float range, float speed, float hourly_cost, float fuel_burn) {
        this.name = name;
        this.TOW_min = TOW_min;
        this.TOW_max = TOW_max;
        this.range = range;
        this.speed = speed;
        this.hourly_cost = hourly_cost;
        this.fuel_burn = fuel_burn;
    }

    // Getter Methods
    public String getName() {
        return name;
    }

    public float getTowMin() {
        return TOW_min;
    }

    public float getTowMax() {
        return TOW_max;
    }

    public float getRange() {
        return range;
    }

    public float getSpeed() {
        return speed;
    }

    public float getHourlyCost() {
        return hourly_cost;
    }

    public float getFuelBurn() {
        return fuel_burn;
    }

    // Profit calculator - returns profit
    public static float calcProfit(float mass, float distance, float payment, Airplane airplane){
        float time = distance/airplane.getSpeed(); // Determines total fly time
        float staffCosts = time * airplane.getHourlyCost(); // Staff costs based on hourly costs
        float fuelCosts = (airplane.getTowMin() + mass) * airplane.getFuelBurn() * time; // Fuel Costs based on time and mass

        float profit = payment - (staffCosts+ fuelCosts);
        return profit;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        // Entering Fleet Data - Number of plane entries and make an array using this
        int num = s.nextInt();
        s.nextLine();
        Airplane[] AirplaneObjs = new Airplane[num];

        // Create plane objects
        for (int i = 0; i < num; i++){
            String input = s.nextLine();
            String[] data = input.split(" ");
            AirplaneObjs[i] = new Airplane(data[0], Float.parseFloat(data[1]),Float.parseFloat(data[2]), Float.parseFloat(data[3]), 
                Float.parseFloat(data[4]), Float.parseFloat(data[5]), Float.parseFloat(data[6]));
        }

        Float totalProfit = Float.parseFloat("0"); // Track total profit from all contracts
        
        // Entering Contract Data
        while (s.hasNext()) {
            String input = s.nextLine();
            if (input.equals("quit")){ //Checks if input is to quit loop
                break;
            }

            String[] data = input.split(" ");
            Float mass =  Float.parseFloat(data[0]);
            Float distance =  Float.parseFloat(data[1]);
            Float payment =  Float.parseFloat(data[2]);
            
            Float bestProfit = Float.parseFloat("0"); // Tracks plane with best profit given contract
            String bestPlane = "";

            for (Airplane airplane : AirplaneObjs) { // Loop through list of airplanes
                if (airplane.getTowMin()+ mass > airplane.getTowMax()) {
                    continue; // Continue if cargo mass + min max is greater than max mass
                }
                if (distance > airplane.getRange()){
                    continue; // Continue if distance is greater than range
                }
                Float profit = calcProfit(mass, distance, payment, airplane);
                if (profit > 0){
                    if (profit > bestProfit) {
                        bestProfit = profit; // Only change when profit is above 0 and better than bestProfit
                        bestPlane = airplane.getName();
                    }
                }
            }

            if (bestPlane.equals("")){
                System.out.println("decline"); // Print out decline if no best Plane
            } else {
                System.out.printf("%s %.2f%n", bestPlane, bestProfit);
                totalProfit += bestProfit; // Add to total profit
            }  
        }

        // Print total profit
        System.out.printf("Profit: %.2f%n",totalProfit);
        s.close();
    }
} 