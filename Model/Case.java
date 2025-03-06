package Model;

public class Case {

    /****************
     *  ATTRIBUTES  *
     ****************/

    //Pour stocker les coordonnées de la case
    private int x;
    private int y;

    private boolean traversable; //indique si la case est traversable (donc ni mur ni table)
    private boolean pain;        //indique si la case contient un pain (pour le prendre et le vendre)
    //ça c'était juste pour mes tests individuels, je sais pas si on va le garder
    //à tous les coups ça dégage mais just in case i'm keeping it its not that hard to delete anw


    /*************************************
     *              GETTERS
     *************************************/
    public boolean isTraversable(){ return this.traversable; }
    public boolean isPain() { return pain; }
    public int getX() { return x; }
    public int getY() { return y; }

    /*************************************
     *              SETTERS
     *************************************/
    public void setTraversable(boolean traversable) { this.traversable = traversable; }
    public void setPain(boolean pain) { this.pain = pain; }

    /*************************************
     *              CONSTRUCTORS
     *************************************/

    //Par défaut : une case traversable
    public Case(int x, int y) {
        this.traversable = true;
        this.pain = false;
        this.x = x; this.y = y;
    }

    //A voir si on change la surcharge ou pas idk
    public Case(int x, int y, boolean traversable, boolean pain) {
        this.traversable = traversable;
        this.pain = pain;
        this.x = x; this.y = y;
    }


}
