package ensta;

import ensta.Board;

public class TestBoard {
    public static void main(String[] args){
        String name="ensta.Board 1";
        int taille= 7;
        Board B1 = new Board(name,taille);
        Board B2 = new Board("ensta.Board 2");
        B1.print();
        B2.print();
    }
}
