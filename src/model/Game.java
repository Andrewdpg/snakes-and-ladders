package model;
import java.util.*;

public class Game {

    public static final int MAX_PLAYERS = 3;

    private PlayerList playerList;
    private Board board;
    private String availableSymbols;
    private int dice;

    public Game() {
        playerList = new PlayerList();
        availableSymbols = "*!OX%$#+&";
    }

    public void init(int rows, int columns, int snakes, int ladders) {
        board = new Board(rows, columns);
        // Inicio la lista y añado un jugador, solo para que temporalmente no de error.
        // Estos métodos deben cambiar.
        board.initSnakesAndLadders(snakes, ladders);
    }

    public boolean addPlayer(char symbol) {
        if (availableSymbols.contains(symbol + "")) {
            playerList.addPlayer(symbol);
            availableSymbols = availableSymbols.replace(symbol + "", "");
            return true;
        }
        System.out.println("El simbolo seleccionado no se encuentra disponible.");
        return false;
    }

    public Player getCurrentPlayer() {
        return playerList.getCurrent();
    }

    public String getAvailableSymbols() {
        return availableSymbols;
    }

    public int throwDice(){
        Random random = new Random();
        dice= random.nextInt(6) + 1;
        return dice;
    }

    private void movePlayer(Player current){
        current.setPosition(current.getPosition()+ dice);
    }
    
    public void movePlayer(){
        movePlayer(getCurrentPlayer());
    }
}
