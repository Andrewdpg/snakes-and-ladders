package model;

import ui.Reader;

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

        initSnakes(snakes, getBoxStartingFrom(-Reader.randInt(1, length), end)); // Inicializa primero las serpientes
        initLadders(ladders, getBoxStartingFrom(Reader.randInt(1, length), start)); // Inicializa, luego, las escaleras
    }

    private void initSnakes(int snakes, Box current) {
        if (snakes == 0) {// Si no tiene nada más por generar, termina
            return;
        }
        if (current != end && current != start) {// No puede haber escalera o serpiente en inicio o fin
            if (!current.hasSnake() &&
                    !current.hasLadder()) {// No puede contener ya una escalera o una serpiente
                // Asigna una casilla, al azar, que esté detrás.
                current.setSnake(getBoxStartingFrom(-Reader.randInt(1, current.getId()), current));
                // Se llama a sí mismo nuevamente para completar el proceso de creación.
                initSnakes(snakes - 1, getBoxStartingFrom(-Reader.randInt(1, length), current));
                return;
            }
        }
        initLadders(snakes, getBoxStartingFrom(-Reader.randInt(1, length), current));
    }

    private void initLadders(int ladders, Box current) {
        if (ladders == 0) { // Si no tiene nada más por generar, termina
            return;
        }
        if (current != end && current != start) { // No puede haber escalera o serpiente en inicio o fin
            if (!current.hasSnake() &&
                    !current.hasLadder()) { // No puede contener ya una escalera o una serpiente
                // Asigna una casilla, al azar, que esté delante.
                current.setLadder(getBoxStartingFrom(Reader.randInt(1, length - current.getId()), current));
                // Se llama a sí mismo nuevamente para completar el proceso de creación.
                initLadders(ladders - 1, getBoxStartingFrom(Reader.randInt(1, length), current));
                return;
            }
        }
        initLadders(ladders, getBoxStartingFrom(Reader.randInt(1, length), current));
    }

    public Box getBox(int num) {
        return getBoxStartingFrom(num, start);
    }

    private Box getBoxStartingFrom(int num, Box current) {
        if (num == 0) { // Si num ya es 0, es porque llegó a la casilla buscada, por lo que retorna
            return current;
        }
        if (num > 0) { // Si num es positivo, retorna la casilla que está n posiciones delante
            return getBoxStartingFrom(num - 1, current.getNext());
        } else { // Si num es negativo, retorna la casilla que está n posiciones detrás
            return getBoxStartingFrom(num + 1, current.getPrevious());
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
