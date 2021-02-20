package ensta.navires;

public class Destroyer extends AbstractShip {
    public Destroyer(ORIENTATION orientation){

        super("Destroyer",'D',2,orientation);
    }
    public Destroyer(){
        super("Destroyer",'D',2);
    }
}
