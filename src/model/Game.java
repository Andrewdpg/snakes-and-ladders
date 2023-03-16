package model;

import java.util.Random;

public class Game {
    private Node root;

    public static final int MAX_PLAYERS = 3;

    private PlayerList playerList;
    private Board board;
    private LeaderBoard leaderBoard;
    private String availableSymbols;
    private int dice;
    private boolean hasFinished;
    private long initTime;

    public Game() {
        playerList = new PlayerList();
        hasFinished = false;
        availableSymbols = "*!OX%$#+&";
        initTime = System.currentTimeMillis();
    }

    public void init(int rows, int columns, int snakes, int ladders) {
        board = new Board(rows, columns);
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

    public Player nextPlayer() {
        return playerList.getNextPlayer();
    }

    public String getAvailableSymbols() {
        return availableSymbols;
    }

    public void showPlayersBoard() {
        board.printPlayersBoard(playerList);
        System.out.println();
    }

    public void showSnakesAndLadders(){
        board.printSnakesAndLaddersBoard();
        System.out.println();
    }

    public int throwDice() {
        Random random = new Random();
        dice = random.nextInt(6) + 1;
        return dice;
    }

    private void movePlayer(Player current) {
        System.out.println("Posición anterior: " + current.getPosition());
        if (current.getPosition() + dice > board.getLength()) {
            return;
        }
        Box nextBox = board.getBox(current.getPosition() + dice-1);
        if (nextBox.hasLadder()|| nextBox.hasSnake()) {
            if (nextBox.hasSnake()) {
                current.setPosition(nextBox.getSnake().getId());
                System.out.println("Era casilla serpiente");
            } else {
                current.setPosition(nextBox.getLadder().getId());
                System.out.println("Era casilla escalera");
            }
            System.out.println("Nueva posición: " + current.getPosition());
        } else {
            current.setPosition(current.getPosition() + dice);
            System.out.println("Nueva posición: " + current.getPosition());
        }
    }

    public void movePlayer() {
        movePlayer(getCurrentPlayer());
        if (getCurrentPlayer().getPosition() == board.getEnd().getId()) {
            System.out.println("Jugador " + getCurrentPlayer().getSymbol() + " haz ganado el juego.");
            hasFinished = true;
        }
    }

    public void addScoreToTree(Player player){
        leaderBoard.add(new Node(player));
    }

    public boolean hasFinished() {
        return hasFinished;
    }
    
    public long getInitTime() {
        return initTime;
    }
}
