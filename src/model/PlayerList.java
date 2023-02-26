package model;

public class PlayerList {

    private Player current;

    public PlayerList() {
    }

    // Método de añadir jugadores
    // Puse esto solo para que temporalmente no de error
    // Este método, claramente, debe cambiar
    public void addPlayer(char symbol) {
        current = new Player(symbol);
    }

    public Player getCurrent() {
        return current;
    }
}
