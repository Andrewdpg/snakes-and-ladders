package model;

public class Box {

    private int id;
    private String connections;
    private Box next;
    private Box previous;
    private Box snake;
    private Box ladder;

    public Box(int id) {
        this.id = id;
        this.connections = "";
    }

    public Box(int id, Box previous) {
        this.id = id;
        this.previous = previous;
        this.connections = "";
    }

    public int getTotalConnections(){
        return this.connections.split(",").length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Box getNext() {
        return next;
    }

    public void setNext(Box next) {
        this.next = next;
    }

    public Box getPrevious() {
        return previous;
    }

    public void setPrevious(Box previous) {
        this.previous = previous;
    }

    public boolean hasSnake() {
        return snake != null;
    }

    public boolean hasLadder() {
        return ladder != null;
    }

    public Box getSnake() {
        return snake;
    }

    public Box getLadder() {
        return ladder;
    }

    public void setSnake(Box snake) {
        this.snake = snake;
    }

    public void setLadder(Box ladder) {
        this.ladder = ladder;
    }

    public String getConnections() {
        return connections;
    }

    public void setConnections(String connections) {
        this.connections = connections;
    }

    public void addConnection(String connection) {
        this.connections += (!this.connections.isBlank() ? "," : "") + connection;
    }

    public void addStartConnection(String connection) {
        this.connections = connection + (!this.connections.isBlank() ? "," : "") + this.connections;
    }
}
