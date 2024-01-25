/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 3
*/

public class BooleanSource {
    private double probability;
    
    // Constructor method checks if given probaility is valid
    public BooleanSource(double p) {
        if (p < 0.0 || p > 1.0) {
            throw new IllegalArgumentException("Not a valid probability");
        } else{
            this.probability = p;
        }  
    }

    // "Occurs" method from class, returns true or false based on how math.random compares to inputted probability
    public boolean requestArrived() {
        return (Math.random() < probability);
    }
}