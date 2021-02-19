package ensta;

import ensta.Board;
import ensta.navires.Battleship;
import ensta.navires.Carrier;
import ensta.navires.ORIENTATION;
import ensta.navires.Submarine;

public class TestBoard {
    public static void main(String[] args){
        String name="Board 1";
        Board board1= new Board(name);
        Board board2= new Board("Board 2",10);
        Board board3= new Board("Board 3");
        Board board4= new Board("Board 4");
        Board board5= new Board("Board 5");
        Submarine S1 = new Submarine(ORIENTATION.NORTH);
        Battleship B1 = new Battleship(ORIENTATION.WEST);
        Carrier C1 = new Carrier(ORIENTATION.SOUTH);
        Carrier C2 = new Carrier(ORIENTATION.WEST);
        Carrier C3 = new Carrier(ORIENTATION.NORTH);
        Carrier C4 = new Carrier(ORIENTATION.EAST);
        Carrier C5 = new Carrier(ORIENTATION.SOUTH);
        try {
            board1.putShip(C1,5,5);
            board1.print();
        }
        catch(Exception e){}
        try {
            board4.putShip(C4,5,5);
            board4.print();
        }
        catch(Exception e){}
        try {
            board2.putShip(C2,5,4);
            board2.print();
        }
        catch(Exception e){}
        try {
            board3.putShip(C3,4,5);
            board3.print();
        }
        catch(Exception e){}
        try {
            board5.putShip(C5,5,4);
            board5.putShip(B1,6,5);
            board5.print();
        }
        catch(Exception e){}
    }
}
