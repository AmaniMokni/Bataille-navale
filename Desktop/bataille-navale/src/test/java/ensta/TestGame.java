package ensta;
import ensta.navires.*;
public class TestGame {
    public static void main(String[] args) {
        Board board = new Board("Grille de test",8);
        board.print();
        AbstractShip D = new Destroyer();
        AbstractShip S1 = new Submarine();
        AbstractShip S2 = new Submarine();
        AbstractShip B = new Battleship();
        AbstractShip C = new Carrier();

        AbstractShip[] ships ={D,S1,S2,B,C};

        BattleShipsAI ai = new BattleShipsAI(board,board);
        ai.putShips(ships);

        int NaviresDetruits =0;
        int TotalNavires = 5;

        int[] coords = new int[2];

        while(NaviresDetruits < TotalNavires ){
            Hit hit = ai.sendHit(coords);
            if(hit != Hit.MISS && hit != Hit.STRIKE)
                NaviresDetruits++;
            System.out.println("abscisse:"+coords[0]);
            System.out.println("ordonnée:"+coords[1]);
            System.out.println(hit.toString());
            board.print();
            sleep(1000);


        }
    }
    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
