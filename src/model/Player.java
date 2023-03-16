package model;

public class Player {

    private char symbol;
    private int position;

    private int score;
    private String name;

    private Player next;
    private Player previous;

    public Player(char symbol) {
        this.symbol = symbol;
        this.position = 1;
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

    /**
     * @return int return the points
     */
    public int getScore() {
        return score;
    }

    /**
     * @param points the points to set
     */
    public void setScore(int points) {
        this.score = points;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return  this.getScore() + " - "+this.symbol + " - " + this.name;
    }
}
