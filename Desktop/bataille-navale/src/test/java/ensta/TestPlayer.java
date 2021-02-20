package ensta;
import ensta.navires.*;
import java.util.ArrayList;

public class TestPlayer {
    public static void main(String[] args) {
        ArrayList<AbstractShip> ListeNavire = new ArrayList<>();

        AbstractShip D = new Destroyer();
        AbstractShip S1 = new Submarine();
        AbstractShip S2 = new Submarine();
        AbstractShip B = new Battleship();
        AbstractShip C = new Carrier();

        ListeNavire.add(D);
        ListeNavire.add(S1);
        ListeNavire.add(S2);
        ListeNavire.add(B);
        ListeNavire.add(C);

        Board b1 = new Board("Joueur 1");
        Board b2 = new Board("Joueur 2");

        Player p = new Player(b1, b2 , ListeNavire);

        p.putShips();

    }

}

