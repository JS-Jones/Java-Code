public class Player {
    private String color;
    private boolean isHuman;

    // Constructor taking in color and whether the player is human/ai
    public Player(String color, boolean isHuman) {
        this.color = color;
        this.isHuman = isHuman;
    }

    // Returns the color
    public String getColor() {
        return color;
    }

    // Returns a boolean whether human or not
    public boolean isHuman() {
        return isHuman;
    }

    // set the player to a human or not
    public void setHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }
}
