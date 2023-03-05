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

    public Player getNext() {
        return next;
    }

    public void setNext(Player next) {
        this.next = next;
    }

    public Player getPrevious() {
        return previous;
    }

    public void setPrevious(Player previous) {
        this.previous = previous;
    }

}
