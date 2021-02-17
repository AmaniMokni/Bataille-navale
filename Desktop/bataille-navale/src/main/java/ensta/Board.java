package ensta;

/* La classe ensta.Board: composée d’un nom, d’un tableau 2D de character pour les ensta.navires
et d’un tableau 2D de boolean pour les frappes. */
public class Board {
    private String nom;
    private int taille;
    private Character[][] tab_navires;
    private boolean[][] tab_frappes;
/* Constructeur valué prenant en arguments le nom et la taille de la grille */
public Board(String nom, int taille){
    this.nom=nom;
    this.taille=taille;
    tab_navires = new Character[taille][taille];
    tab_frappes= new boolean[taille][taille];
}
/* Constructeur avec la taille par défaut =10 */
public Board(String nom){
        this(nom,10);
    }
/*  Méthode print() qui dessine les deux grilles de jeu dans leur état respectif */
        public void print(){
       int n=getTaille();
       /* La grille des Navires : affiche le label du navire sur les positions où ce navire se trouve, et « . » sinon*/
       System.out.println("Navires:");
       char Nom_C='A';
       System.out.print('\t');
       for(int i=0;i<n;i++){
           System.out.print(Nom_C);
           System.out.print('\t');
           Nom_C=(Nom_C=='Z'? 'A':++Nom_C);
}
       System.out.print('\n');
       for (int i=0; i<n;i++){
           System.out.print(i+1);
           System.out.print('\t');
           for (int j=0;j<n;j++){
               if (tab_navires[i][j]==null){
                   System.out.print('.');
               }
               else {
                   System.out.print(tab_navires[i][j]);
               }
               System.out.print('\t');
           }
           System.out.print('\n');
       }
    /* La grille des Frappes : affiche « x » pour une position où une frappe a touché un bateau, et « . » sinon */
    System.out.println("Frappes:");
    Nom_C='A';
    System.out.print('\t');
    for(int i=0;i<n;i++){
        System.out.print(Nom_C);
        System.out.print('\t');
        Nom_C=(Nom_C=='Z'? 'A':++Nom_C);
    }
    System.out.print('\n');
    for (int i=0; i<n;i++){
        System.out.print(i+1);
        System.out.print('\t');
        for (int j=0;j<n;j++){
            if (tab_frappes[i][j]==false){
                System.out.print('.');
            }
            else {
                System.out.print(tab_frappes[i][j]);
            }
            System.out.print('\t');
        }
        System.out.print('\n');       }

}
public int getTaille(){
    return this.taille;
}
public void setTaille(int taille){
    this.taille=taille;
}
public String getNom() {
        return this.nom;
    }
public void setNom(String nom){
        this.nom=nom;
    }

}