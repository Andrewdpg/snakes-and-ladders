package model;

public class Board {

    private int rows;
    private int columns;
    private int length;

    private Box start;
    private Box end;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.length = rows * columns;
        initBoard(1);
    }

    private void initBoard(int index) {
        addBox(index);
        if (index < length) {
            initBoard(index + 1);
        }
    }

    private void addBox(int index) {
        if (start == null) {
            Box box = new Box(index);
            box.setPrevious(box);
            box.setNext(box);
            start = box;
            end = box;
        } else {
            end.setNext(new Box(index, end));
            end.getNext().setNext(start);
            end = end.getNext();
            start.setPrevious(end);
        }
    }

    public void initSnakesAndLadders(int snakes, int ladders) {
        // Si el usuario pide más serpientes y escaleras que número de casillas
        if (snakes + ladders > length - 2) {
            System.out.println("Número de escaleras y serpientes demasiado grande, tomando el número máximo posible");
            snakes = maxSnakes(snakes, ladders); // Toma el máximo de serpientes en base a las casillas
            ladders = maxLadders(snakes, ladders); // Toma el máximo de escaleras en base a las casillas
        }
    }

    private int maxSnakes(int snakes, int ladders) {
        if (snakes + ladders > length - 2) {
            maxSnakes(snakes - 1, ladders - 1);
        }
        return snakes;
    }

    private int maxLadders(int snakes, int ladders) {
        if (snakes + ladders > length - 2) {
            maxSnakes(snakes - 1, ladders - 1);
        }
        return ladders;
    }
}
