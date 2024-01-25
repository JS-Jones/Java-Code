public class Pawn {
    private String color;
    private char symbol;

    // Constructor taking in a color and setting the symbol based on that
    public Pawn(String color) {
        this.color = color;
        this.symbol = (color.equalsIgnoreCase("black")) ? '♟' : '♙';
    }

    public String getColor() {
        return color;
    }

    public char getSymbol() {
        return symbol;
    }

}
