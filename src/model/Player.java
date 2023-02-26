package model;

public class Player {

    private char symbol;
    private int position;

    private Player next;
    private Player previous;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return "" + symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
