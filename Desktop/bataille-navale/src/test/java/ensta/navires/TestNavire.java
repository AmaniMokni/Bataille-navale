package ensta.navires;

public class TestNavire {
    public static void main(String[] args){
        Submarine S1 = new Submarine(ORIENTATION.NORTH);
        Battleship B1 = new Battleship(ORIENTATION.WEST);
        Carrier C1 = new Carrier(ORIENTATION.EAST);
        System.out.println(S1.getLabel());
        System.out.println(B1.getTailleNavire());
        System.out.println(C1.getTypeNavire());
    }
}
