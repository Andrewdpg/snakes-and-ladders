package model;

public class PlayerList {

    private Player current;

    public PlayerList() {
    }

    public void addPlayer(char symbol) {
        Player player = new Player(symbol);
        if (current == null) {
            current = player;
            current.setNext(current);
            current.setPrevious(current);
        } else {
            current.getPrevious().setNext(player);
            player.setPrevious(current.getPrevious());
            player.setNext(current);
            current.setPrevious(player);
        }
    }

    public Player getCurrent() {
        return current;
    }

    public String getPlayersAt(int boxID) {
        return getPlayersAt(boxID, this.current);
    }

    private String getPlayersAt(int boxID, Player current) {
        if (current.getNext() == this.current) {
            if (current.getPosition() == boxID) {
                return current.getSymbol();
            } else {
                return "";
            }
        }
        if (current.getPosition() == boxID) {
            return current.getSymbol() + getPlayersAt(boxID, current.getNext());
        }
        return "" + getPlayersAt(boxID, current.getNext());

    }
}
