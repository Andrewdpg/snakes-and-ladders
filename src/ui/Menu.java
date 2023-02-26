package ui;

import model.Game;

public class Menu {

    private int option;
    private Game game;
    private boolean isRunning;
    private final String MAIN_MENU = "1. Jugar\n"
            + "2. Salir\n"
            + "Opcion:";
    private final String GAME_MENU = "Jugador {player}, es tu turno\n"
            + "1. Tirar dado\n"
            + "2. Ver escaleras y serpientes\n"
            + "Opcion: ";

    public Menu() {
        isRunning = true;
    }

    // Método de inicialización del juego
    // Puse esto solo para que temporalmente no de error
    private void initGame() {
        game = new Game();
        game.init(0, 0, 0, 0);
    }

    // Método para tirar el dado
    private void throwDice() {

    }

    // Método para mostar las escaleras y serpientes.
    private void printSnakesAndLadders() {

    }

    public void printMenu() {
        if (isInitialized()) {
            System.out.print(GAME_MENU.replace("{player}", game.getCurrentPlayer().getSymbol()));
        } else {
            System.out.print(MAIN_MENU);
        }
    }

    public void executeInput() {
        if (isInitialized()) {
            executeGameMenu();
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

    public void readOption() {
        option = Reader.readInt(-1);
    }

    private boolean isInitialized() {
        return game != null;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
