package Model;

public class Tile {

    /****************
     *  ATTRIBUTES  *
     ****************/

    //Indicates the indexes of the tile in its array 
    protected int x;
    protected int y;

    protected boolean accesible; //indicates if the tile is traversable/accesible
    protected boolean hasBaker; //indicates if the tile has a baker on it
    protected boolean hasRacoon; //indicates if the tile has a racoon on it
    protected boolean hasOven; //indicates if the tile has a bread on it

    /*************************************
     *              GETTERS
     *************************************/
    public boolean isAccessible(){ return this.accesible; }
    public int getX() { return x; }
    public int getY() { return y; }

    public boolean hasBaker() { return hasBaker; }
    public boolean hasRacoon() { return hasRacoon;}
    public boolean hasOven() { return hasOven; }

    /*************************************
     *              SETTERS
     *************************************/
    public void setTraversable(boolean traversable) { this.accesible = traversable; }
    public void BakerArrived() { this.hasBaker = true; }
    public void BakerLeft() { this.hasBaker = false; }
    public void RacoonArrived() { this.hasRacoon = true; }
    public void RacoonLeft() { this.hasRacoon = false; }

    /*************************************
     *              CONSTRUCTORS
     *************************************/

    //By default, a tile is accessible
    public Tile(int x, int y) {
        this.accesible = true;
        this.hasBaker = false;
        this.hasRacoon = false;
        this.hasOven = false;
        this.x = x; this.y = y;
    }

}