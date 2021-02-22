package ensta.navires;

public abstract class AbstractShip {
    protected Character label;
    protected String typeNavire;
    protected int tailleNavire;
    protected ORIENTATION orientation;
    private int strikeCount=0;
public int getTailleNavire(){

    return this.tailleNavire;
}
public void setTailleNavire(int tailleNavire){

    this.tailleNavire=tailleNavire;
}
public String getTypeNavire(){

    return this.typeNavire;
}
public void setTypeNavire(String typeNavire){

    this.typeNavire=typeNavire;
}
public Character getLabel(){

    return this.label;
}
public void setLabel(Character label){

    this.label=label;
}
public void setOrientation(ORIENTATION orientation){

    this.orientation = orientation;
    }
    public ORIENTATION getOrientation() {

    return this.orientation;
    }
public AbstractShip(String typeNavire,Character label, int tailleNavire, ORIENTATION orientation){
        this.typeNavire=typeNavire;
        this.label=label;
        this.tailleNavire=tailleNavire;
        this.orientation=orientation;
}
public AbstractShip(String typeNavire,Character label, int tailleNavire){
    this.typeNavire=typeNavire;
    this.label=label;
    this.tailleNavire=tailleNavire;}
public void addStrike(){
    if (!isSunk())
        ++strikeCount;
}
public boolean isSunk(){

    return strikeCount >= this.tailleNavire;
}
}

