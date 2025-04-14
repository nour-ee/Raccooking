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
    protected boolean nextToBaker; //indicates if the tile is next to the baker

    /*************************************
     *              GETTERS
     *************************************/
    public boolean isAccessible(){ return this.accessible; }
    public boolean isAccessibleToBaker(){ return this.accessible && !hasRacoon; }
    public boolean isAccessibleToRaccoon(){ return this.accessible && !(nextToBaker||hasBaker); }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isNextToBaker() { return nextToBaker; }
    public boolean hasBaker() { return hasBaker; }
    public boolean hasRaccoon() { return hasRacoon;}
    public boolean hasOven() { //if(hasOven){System.out.println("oven found");}
        return hasOven; }

    public boolean isEmpty() { return !hasBaker && !hasRacoon && !hasOven; }

    /*************************************
     *              SETTERS
     *************************************/
    public void setAccessible(boolean b) { this.accessible = b; }
    public void setNextToBaker(boolean b) { this.nextToBaker = b; }
    public void BakerArrived() { this.hasBaker = true; }
    public void BakerHasLeft() { this.hasBaker = false; }
    public void RaccoonArrived() { this.hasRacoon = true; }
    public void RaccoonHasLeft() { this.hasRacoon = false; }

    /*************************************
     *              CONSTRUCTORS
     *************************************/

    //By default, a tile is accessible and contains nothing
    public Tile(int x, int y) {
        this.accessible = true;
        this.hasBaker = false;
        this.hasRacoon = false;
        this.hasOven = false;
        this.nextToBaker = false;
        this.x = x; this.y = y;
    }

    /*************************************
     *              METHODS
     *************************************/

    /**
     * Function that tells you whether or not a given tile is a neighbour of the current tile
     * @param t the tile to check
     */
    public boolean isNeighbour(Tile t){
        int x = t.getX();
        int y = t.getY();
        if(this.x == x && Math.abs(this.y - y)== 1) return true;
        if(this.y == y && Math.abs(this.x - x) == 1) return true;
        if(Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 1) return true;
        return false;
    }
}