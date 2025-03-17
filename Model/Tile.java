package Model;

public class Tile {

    /****************
     *  ATTRIBUTES  *
     ****************/

    //Indicates the indexes of the tile on the map
    protected int x;
    protected int y;

    protected boolean accessible; //indicates if the tile is accessible
    protected boolean hasBaker; //indicates if the tile has a baker on it
    protected boolean hasRacoon; //indicates if the tile has a racoon on it
    protected boolean hasOven; //indicates if the tile has a bread on it

    /*************************************
     *              GETTERS
     *************************************/
    public boolean isAccessible(){ return this.accessible; }
    public int getX() { return x; }
    public int getY() { return y; }

    public boolean hasBaker() { return hasBaker; }
    public boolean hasRacoon() { return hasRacoon;}
    public boolean hasOven() { //if(hasOven){System.out.println("oven found");}
        return hasOven; }

    public boolean isEmpty() { return !hasBaker && !hasRacoon && !hasOven; }

    /*************************************
     *              SETTERS
     *************************************/
    public void setAccessible(boolean b) { this.accessible = b; }
    public void BakerArrived() { this.hasBaker = true; }
    public void BakerHasLeft() { this.hasBaker = false; }
    public void RacoonArrived() { this.hasRacoon = true; }
    public void RacoonHasLeft() { this.hasRacoon = false; }

    /*************************************
     *              CONSTRUCTORS
     *************************************/

    //By default, a tile is accessible and contains nothing
    public Tile(int x, int y) {
        this.accessible = true;
        this.hasBaker = false;
        this.hasRacoon = false;
        this.hasOven = false;
        this.x = x; this.y = y;
    }

}