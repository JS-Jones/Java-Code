public class Tuple {
    private float value;
    private State action;

    // Constructor for Tuple
    public Tuple(float value, State action){
        this.value = value;
        this.action = action;
    }

    // Returns the value
    public float getValue(){
        return this.value;
    }

    // Returns the action state
    public State getAction(){
        return this.action;
    }
}
