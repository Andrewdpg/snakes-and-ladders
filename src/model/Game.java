package model;

public class Game {

    private PlayerList playerList;
    private int rows;
    private int columns;

    public Game() {

    }

    public void init(int rows, int columns, int snakes, int ladders) {
        this.rows = rows;
        this.columns = columns;
        // Inicio la lista y añado un jugador, solo para que temporalmente no de error.
        // Estos métodos deben cambiar.
        playerList = new PlayerList();
        playerList.addPlayer('$');
    }

    // Método que retornará al jugador que tenga turno
    public Player getCurrentPlayer() {
        return playerList.getCurrent();
    }
}
