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

        AbstractShip C1 = new Carrier(ORIENTATION.SOUTH);

        ListeNavire.add(D);
        ListeNavire.add(S1);
        ListeNavire.add(S2);
        ListeNavire.add(B);
        ListeNavire.add(C);

        Board b1 = new Board("Joueur 1");
        Board b2 = new Board("Joueur 2");


        Player p1 = new Player(b1, b2 , ListeNavire);
        p1.putShips();

        try{
            b2.putShip(C1,1,1);
            int[] coords={};
            p1.sendHit(coords);
            p1.board.print();
            p1.opponentBoard.print();
            p1.sendHit(coords);
            p1.board.print();
            p1.opponentBoard.print();
        }
        catch (Exception e){}

    }

}

