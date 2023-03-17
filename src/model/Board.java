package model;

import ui.Reader;

public class Board {

    private final String LETTERS = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private int rows;
    private int columns;
    private int length;

    private Box start;
    private Box end;

    private int iConnection;
    private int jConnection;
    private int ladderIndex;

    private String printFormat;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.length = rows * columns;
        initBoard(1);
        printFormat = getFormat(columns);
        iConnection = 0;
        jConnection = 0;
        ladderIndex = 1;
    }

    private String getFormat(int q) {
        if (q == 1) {
            return "%-8s";
        }
        return "%-8s " + getFormat(q - 1);
    }

    public String getAvailableSnakeConnection() {
        iConnection++;
        if (iConnection >= LETTERS.length()) {
            iConnection = 1;
            jConnection++;
        }
        if (jConnection >= LETTERS.length()) {
            return jConnection + "" + LETTERS.charAt(iConnection);
        }
        return LETTERS.charAt(jConnection) + "" + LETTERS.charAt(iConnection);
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
        if (snakes + ladders > (length - 2) / 2) {
            System.out.println("Número de escaleras y serpientes demasiado grande, tomando el número máximo posible");
            // Toma el máximo de serpientes y escaleras en base a las casillas
            ladders = ladders - ((snakes + ladders) - (length - 2) / 2) / 2;
            snakes = snakes - ((snakes + ladders) - (length - 2) / 2);
            System.out.println("Escaleras generadas: " + ladders);
            System.out.println("Serpientes generadas: " + snakes);
        }

        initSnakes(snakes, getBoxStartingFrom(-Reader.randInt(1, length), end)); // Inicializa primero las serpientes
        initLadders(ladders, getBoxStartingFrom(Reader.randInt(1, length), start)); // Inicializa, luego, las escaleras
    }

    private void initSnakes(int snakes, Box current) {
        if (snakes == 0) {// Si no tiene nada más por generar, termina
            return;
        }
        if (current != end && current != start) {// No puede haber escalera o serpiente en inicio o fin
            if (current.getTotalConnections() == 0) {// No puede contener ya una escalera o una serpiente
                // Asigna una casilla, al azar, que esté detrás.
                Box snake = getBoxStartingFrom(-Reader.randInt(1, current.getId()), current);
                if (snake.getTotalConnections() == 0 && snake != start && snake != end) {
                    current.setSnake(snake);
                    String connection = getAvailableSnakeConnection().trim();
                    current.addStartConnection(connection);
                    current.getSnake().addConnection(connection);
                    // Se llama a sí mismo nuevamente para completar el proceso de creación.
                    initSnakes(snakes - 1, getBoxStartingFrom(-Reader.randInt(1, length), current));
                    return;
                }
            }
        }
        initSnakes(snakes, getBoxStartingFrom(-Reader.randInt(1, length), current));
    }

    private void initLadders(int ladders, Box current) {
        if (ladders == 0) { // Si no tiene nada más por generar, termina
            return;
        }
        if (current != end && current != start) { // No puede haber escalera o serpiente en inicio o fin
            if (current.getTotalConnections() == 0) { // No puede contener ya una escalera o una serpiente
                // Asigna una casilla, al azar, que esté delante.
                Box ladder = getBoxStartingFrom(Reader.randInt(1, length - current.getId()), current);
                if (ladder.getTotalConnections() == 0 && ladder != start && ladder != end) {
                    current.setLadder(ladder);
                    current.addStartConnection(String.valueOf(ladderIndex));
                    current.getLadder().addConnection(String.valueOf(ladderIndex));
                    ladderIndex++;
                    // Se llama a sí mismo nuevamente para completar el proceso de creación.
                    initLadders(ladders - 1, getBoxStartingFrom(Reader.randInt(1, length), current));
                    return;
                }
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

    public void printPlayersBoard(PlayerList players) {
        printPlayersBoard(players, start, 1);
    }

    private void printPlayersBoard(PlayerList players, Box current, int row) {
        if (row > rows) {
            return;
        }
        printPlayersBoard(players, getBoxStartingFrom(columns, current), row + 1);
        System.out.println();
        System.out.printf(printFormat, (Object[]) getRowWithPlayersToString(row, current, players).split("/%/"));
    }

    private String getRowWithPlayersToString(int row, Box current, PlayerList players) {
        if (current.getId() % columns == 0) {
            return "[" + current.getId() + (players.getPlayersAt(current.getId())) + "]";
        }
        if (row % 2 == 0) {
            return getRowWithPlayersToString(row, current.getNext(), players) + "/%/" + "[" + current.getId()
                    + (players.getPlayersAt(current.getId())) + "]";
        } else {
            return "[" + current.getId() + (players.getPlayersAt(current.getId())) + "]" + "/%/"
                    + getRowWithPlayersToString(row, current.getNext(), players);
        }
    }

    public void printSnakesAndLaddersBoard() {
        printSnakesAndLaddersBoard(start, 1);
    }

    private void printSnakesAndLaddersBoard(Box current, int row) {
        if (row > rows) {
            return;
        }
        printSnakesAndLaddersBoard(getBoxStartingFrom(columns, current), row + 1);
        System.out.println();
        System.out.printf(printFormat, (Object[]) getRowToString(row, current).split("/%/"));
    }

    private String getRowToString(int row, Box current) {
        if (current.getId() % columns == 0) {
            return "[" + current.getConnections()  + "]";
        }
        if (row % 2 == 0) {
            return getRowToString(row, current.getNext()) + "/%/" + "[" + current.getConnections() + "]";
        } else {
            return "[" + current.getConnections() + "]" + "/%/"
                    + getRowToString(row, current.getNext());
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Box getStart() {
        return start;
    }

    public void setStart(Box start) {
        this.start = start;
    }

    public Box getEnd() {
        return end;
    }

    public void setEnd(Box end) {
        this.end = end;
    }
}
