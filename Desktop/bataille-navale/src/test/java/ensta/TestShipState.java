package ensta;
import ensta.navires.*;
import ensta.exceptions.*;

public class TestShipState {
    public static void main(String[] args) {
        AbstractShip S = new Submarine(ORIENTATION.WEST);
        Board Bo1 = new Board("test exception", 10);
        try {
            Bo1.putShip(S, 5, 6);
            Bo1.getShipState(5,6).addStrike();
        } catch (Exception e) {
        }
        Bo1.print();
    }
}
