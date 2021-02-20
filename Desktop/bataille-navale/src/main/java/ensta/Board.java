package ensta;

import ensta.exceptions.Chevauchement;
import ensta.exceptions.DejaTouche;
import ensta.exceptions.HorsGrille;
import ensta.exceptions.OrientationNotFound;
import ensta.navires.AbstractShip;

/* La classe ensta.Board: composée d’un nom, d’un tableau 2D de character pour les ensta.navires
et d’un tableau 2D de boolean pour les frappes. */
public class Board implements IBoard {
    private String nom;
    private int taille;
    private ShipState[][] tab_navires;
    private Boolean[][] tab_frappes;

    /* Constructeur valué prenant en arguments le nom et la taille de la grille */
    public Board(String nom, int taille) {
        this.nom = nom;
        this.taille = taille;
        tab_navires = new ShipState[taille][taille];
        tab_frappes = new Boolean[taille][taille];
    }

    /* Constructeur avec la taille par défaut =10 */
    public Board(String nom) {
        this(nom, 10);
    }

    /*  Méthode print() dessine les deux grilles de jeu dans leur état respectif */
    public void print() {
        int n = getSize();
        /* La grille des Navires : affiche le label du navire sur les positions où ce navire se trouve, et « . » sinon*/
        System.out.print("Navires:");
        for(int i=0;i<=n;i++){
            System.out.print('\t');
        }
        System.out.println("Frappes:");
        char Nom_C = 'A';
        System.out.print('\t');
        for (int i = 0; i < n; i++) {
            System.out.print(Nom_C);
            System.out.print('\t');
            Nom_C = (Nom_C == 'Z' ? 'A' : ++Nom_C);
        }
        for(int i=0;i<3;i++){
            System.out.print('\t');
        }

        char NomC = 'A';
        for(int j=n;j<2*n;j++){
            System.out.print(NomC);
            System.out.print('\t');
            NomC = (NomC == 'Z' ? 'A' : ++NomC);
        }

        System.out.print('\n');
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1);
            System.out.print('\t');
            for (int j = 0; j < n; j++) {
                if (tab_navires[i][j] == null) {
                    System.out.print('.');
                } else {
                    System.out.print(tab_navires[i][j]);
                }
                System.out.print('\t');
            }
            for (int k=0;k<2;k++){
                System.out.print('\t');
            }
            System.out.print(i + 1);
            System.out.print('\t');
            for (int j = 0; j < n; j++) {
                if (tab_frappes[i][j]==null) {
                    System.out.print('.');
                } else if (!tab_frappes[i][j]){
                    System.out.print(ColorUtil.colorize("X", ColorUtil.Color.WHITE));
                }
                else {
                    System.out.print(ColorUtil.colorize("X", ColorUtil.Color.RED));
                }
                System.out.print('\t');
            }

            System.out.print('\n');
        }

    }

    public int getSize() {
        return this.taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void putShip(AbstractShip ship, int x, int y) throws HorsGrille,Chevauchement,OrientationNotFound{
        switch (ship.getOrientation()) {
            case NORTH:
                if (x-ship.getTailleNavire()+1<0){
                    throw new HorsGrille();
                }
                for (int i = 0; i < ship.getTailleNavire(); i++) {
                    if(hasShip(x-i,y))
                        throw new Chevauchement();
                    ShipState SS= new ShipState(ship);
                    tab_navires[x - i][y] = SS;
                }
                break;
            case SOUTH:
                if (x+ship.getTailleNavire()>this.getSize()){
                    throw new HorsGrille();}
                for (int i = 0; i < ship.getTailleNavire(); i++) {
                    if(hasShip(x+i,y))
                        throw new Chevauchement();
                    ShipState SS= new ShipState(ship);
                    tab_navires[x + i][y] = SS;
                }
                break;
            case EAST:
                if (y+ship.getTailleNavire()>this.getSize()){
                    throw new HorsGrille();}
                for (int i = 0; i < ship.getTailleNavire(); i++) {
                    if(hasShip(x,y+i))
                        throw new Chevauchement();
                    ShipState SS= new ShipState(ship);
                    tab_navires[x][y + i] = SS;
                }
                break;
            case WEST:
                if (y-ship.getTailleNavire()+1<0) {
                    throw new HorsGrille();
                }
                for (int i = 0; i < ship.getTailleNavire(); i++) {
                    if(hasShip(x,y-i))
                        throw new Chevauchement();
                    ShipState SS= new ShipState(ship);
                    tab_navires[x][y - i] = SS;
                }
                break;
            default:
                throw new OrientationNotFound();
        }
    }
    public boolean hasShip(int x, int y){
        return this.tab_navires[x][y]!=null;
    }
    public void setHit(boolean hit, int x, int y){
        this.tab_frappes[x][y]=hit;
    }
    public Boolean getHit(int x, int y){
        return this.tab_frappes[x][y];
    }
    public ShipState getShipState(int x,int y){
        return this.tab_navires[x][y];
    }
    public void setShipState(ShipState S, int x,int y){
        this.tab_navires[x][y]=S;
    }
    public Hit sendHit(int x,int y) throws DejaTouche {
        if(this.hasShip(x,y))
        { this.tab_navires[x][y].addStrike();
        if(this.tab_navires[x][y].isSunk())
        {
            return Hit.fromInt(this.tab_navires[x][y].getShip().getTailleNavire());
        }
           else  return Hit.STRIKE;
        }
        else return Hit.MISS;
    }
}