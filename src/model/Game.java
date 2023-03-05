package model;

public class Game {

    private PlayerList playerList;
    private Board board;

    public Game() {

    }

    public void init(int rows, int columns, int snakes, int ladders) {
        board = new Board(rows,columns);
        // Inicio la lista y añado un jugador, solo para que temporalmente no de error.
        // Estos métodos deben cambiar.
        board.initSnakesAndLadders(snakes,ladders);
    }

    public void addPlayer(char symbol){
        playerList.addPlayer(symbol);
    }

    public Player getCurrentPlayer() {
        return playerList.getCurrent();
    }
}
