package ensta.exceptions;

public class DejaTouche extends Exception{
    public DejaTouche(){
        System.out.println("Cette palce est déjà touché !");
    }
}
