package ui;

import org.w3c.dom.Node;

import model.Game;

public class Menu {

    private final String MAIN_MENU = "1. Jugar\n"
            + "2. Salir\n"
            + "Opcion:";
    private final String GAME_MENU = "Jugador {player}, es tu turno\n"
            + "1. Tirar dado\n"
            + "2. Ver escaleras y serpientes\n"
            + "Opcion: ";
    private final String PLAY_AGAIN_MENU = "Juego terminado, ¿quieres jugar de nuevo?\n"
            + "1. Si\n"
            + "2. No\n"
            + "Opcion: ";

    private int option;
    private Game game;
    private boolean isRunning;
    private long t1;
    private Node root;
    public Menu() {
        isRunning = true;
    }

    // Método de inicialización del juego
    private void initGame() {
        game = new Game();
        int rows;
        int columns;
        int snakes;
        int ladders;
        
         t1 =System.currentTimeMillis();

        System.out.print("¿Cuantas filas tendrá el tablero? ");
        rows = Reader.readInt(2, 2);
        System.out.print("¿Cuantas columnas tendrá el tablero? ");
        columns = Reader.readInt(2, 2);
        System.out.print("¿Cuantas serpientes tendrá el tablero? ");
        snakes = Reader.readInt(0);
        System.out.print("¿Cuantas escaleras tendrá el tablero? ");
        ladders = Reader.readInt(0);
        game.init(rows, columns, snakes, ladders);

        initPlayers();
    }

    private void initPlayers() {
        int nPlayers;
        System.out.print("¿Cuantas jugadores habrá? (Maximo " + Game.MAX_PLAYERS + ") ");
        nPlayers = Reader.readInt(Game.MAX_PLAYERS, 2);

        // Si el número de jugadores excede el máximo, toma el valor máximo
        if (nPlayers > Game.MAX_PLAYERS) {
            System.out
                    .println("Valor por encima de " + Game.MAX_PLAYERS + ". Numero de jugadores: " + Game.MAX_PLAYERS);
            nPlayers = Game.MAX_PLAYERS;
        }

        addPlayers(nPlayers, 1);
        System.out.println("Juego correctamente inicializado.\n\n");
        game.showPlayersBoard();
    }

    // Método recursivo que agrega n jugadores
    private void addPlayers(int nPlayers, int index) {
        if (nPlayers == 0) {
            return;
        }
        System.out.println("Jugador #" + index + ", selecciona una ficha de juego: " + game.getAvailableSymbols());
        if (game.addPlayer(Reader.readChar('A'))) {
            addPlayers(nPlayers - 1, index + 1);
        } else {
            addPlayers(nPlayers, index);
        }
    }

    // Método para tirar el dado
    public void throwDice() {
        System.out.println(game.throwDice());
        game.movePlayer();
        game.showPlayersBoard();
        game.nextPlayer();
        if (game.hasFinished()) {
            saveScore();
        }
    }

    // Guardar los puntajes cuando termina el juego
    private void saveScore() {
        if (game.hasFinished()==true){
            long t2 = System.currentTimeMillis();
            long spendTime = (t2-t1)/1000;
            int points =(int) (600- spendTime)/6;
            System.out.println("the player: " + game.getCurrentPlayer() + "get: "+ points +"points"  );
            saveScore(points);
        }
    }

    private void saveScore(int points){

    }

    private void printSnakesAndLadders(){
        game.showSnakesAndLadders();
    }

    public void printMenu() {
        if (isInitialized()) {
            if (!game.hasFinished()) {
                System.out.print(GAME_MENU.replace("{player}", game.getCurrentPlayer().getSymbol()));
            } else {
                System.out.print(PLAY_AGAIN_MENU);
            }
        } else {
            System.out.print(MAIN_MENU);
        }
    }

    public void executeInput() {
        if (isInitialized()) {
            if (!game.hasFinished()) {
                executeGameMenu();
            } else {
                executePlayAgainMenu();
            }
        } else {
            executeMainMenu();
        }
    }

    private void executeMainMenu() {
        switch (option) {
            case 1:
                initGame();
                break;
            case 2:
                isRunning = false;
                System.out.println("Terminando programa...");
                break;
            default:
                System.out.println("Opcion no reconocida");
                break;
        }
    }

    private void executeGameMenu() {
        switch (option) {
            case 1:
                throwDice();
                break;
            case 2:
                printSnakesAndLadders();
                break;
            default:
                System.out.println("Opcion no reconocida");
                break;
        }
    }

    private void executePlayAgainMenu() {
        switch (option) {
            case 1:
                initGame();
                break;
            case 2:
                isRunning = false;
                break;
            default:
                System.out.println("Opcion no reconocida");
                break;
        }
    }

    public void readOption() {
        option = Reader.readInt(-1);
    }

    private boolean isInitialized() {
        return game != null;
    }

    public boolean isRunning() {
        return isRunning;
    }

    /**
     * @return int return the option
     */
    public int getOption() {
        return option;
    }

    /**
     * @param option the option to set
     */
    public void setOption(int option) {
        this.option = option;
    }

    /**
     * @return Game return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return boolean return the isRunning
     */
    public boolean isIsRunning() {
        return isRunning;
    }

    /**
     * @param isRunning the isRunning to set
     */
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }


    /**
     * @return long return the t1
     */
    public long getT1() {
        return t1;
    }

    /**
     * @param t1 the t1 to set
     */
    public void setT1(long t1) {
        this.t1 = t1;
    }

}
