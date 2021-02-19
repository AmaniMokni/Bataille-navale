package ensta.exceptions;

public class Chevauchement extends Exception{
    public Chevauchement(){
        System.out.println("Deux navires se chevauchent !");
    }
}
