package ensta;
import java.io.Serializable;
import java.util.List;
import ensta.navires.AbstractShip;
import ensta.navires.ORIENTATION;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;
        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getTypeNavire(), s.getTailleNavire());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            // TODO set ship orientation
            ORIENTATION Orientation;
            switch(res.orientation){
                case "n":
                    Orientation = ORIENTATION.NORTH;
                    break;
                case "s":
                    Orientation = ORIENTATION.SOUTH;
                    break;
                case "e":
                    Orientation = ORIENTATION.EAST;
                    break;
                default:
                    Orientation = ORIENTATION.WEST;
                    break;
            }
            s.setOrientation(Orientation);
            // TODO put ship at given position
            try {this.board.putShip(s,res.y,res.x);}
            catch (Exception e){ --i;}
            // TODO when ship placement successful
            ++i;
            done = i == 5;
            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done=false;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard
            try {
                hit = this.opponentBoard.sendHit(hitInput.y, hitInput.x);
                if(hit==Hit.MISS){
                    board.setHit(false,hitInput.y, hitInput.x);
                }else{
                    board.setHit(true,hitInput.y, hitInput.x);
                }
                if(hit==Hit.MISS || hit==Hit.STRIKE){
                    System.out.println(hit);
                }
                else{
                    System.out.println(hit +" coulé");
                }
                done = true;
            }
            catch(Exception e){done=false;}
            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
            //coords = new int[2];
            coords[0]=hitInput.y;
            coords[1]=hitInput.x;
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}



