package ensta;
import ensta.exceptions.DejaTouche;
import ensta.navires.AbstractShip;

import java.io.Serializable;

public class ShipState implements Serializable {
    private AbstractShip shipRef;
    private boolean struck=false;
    private int add=0;
ShipState() {}
ShipState(AbstractShip Ship){
        this.shipRef = Ship;
    }
    public void addStrike() throws DejaTouche {

            if(this.add < 1){
                shipRef.addStrike();
                this.struck = true;
                ++(this.add);
            }
            else
                throw new DejaTouche();

    }
    public boolean isStruck(){

    return this.struck;

}
    public  String toString(){
        if(this.struck){
            return ColorUtil.colorize(String.valueOf(this.shipRef.getLabel()),ColorUtil.Color.RED);
        }else{
            return String.valueOf(this.shipRef.getLabel());
        }
    }
    public boolean isSunk(){

    return this.shipRef.isSunk();

    }
    public  AbstractShip getShip(){

    return this.shipRef;

    }
}
