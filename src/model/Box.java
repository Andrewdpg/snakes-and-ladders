package model;

public class Box {

    private int id;
    private Box next;
    private Box previous;

    public Box(int id) {
        this.id = id;
    }
    
    public Box(int id, Box previous) {
        this.id = id;
        this.previous = previous;
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

}
