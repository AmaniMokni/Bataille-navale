package ensta.navires;

public class Submarine extends AbstractShip {
    public Submarine(ORIENTATION orientation){

        super("Submarine",'S',3,orientation);
    }
    public Submarine(){

        super("Submarine",'S',3);
    }

}
