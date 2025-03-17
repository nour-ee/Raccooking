package Model;

public class Oven extends Tile {
    /****************
    *  ATTRIBUTES  *
    ****************/
    private Bread bread; // the bread in the oven, when occupied null the rest of the time
    private boolean occupied; // indicates if the oven is occupied

    /****************
    *   GETTERS   *
    ***************/
    public Bread getBread() {
        return bread;
    }
    public boolean isOccupied() {
        return occupied;
    }

    /****************
    *  SETTERS   *
    **************/
    public void setBread(Bread bread) {
        this.bread = bread;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /****************
    * CONSTRUCTOR   *
    *****************/
    public Oven(int x, int y) {
        super(x, y);
        this.accessible = false;
        this.occupied = false;
        this.hasOven=true;
    }

    /**
     * Method to remove the bread from the oven
     */
    public void removeBread(){
        this.bread.killThread();
        this.bread = null; //the bread still exists even though it's not contained anywhere
        this.occupied = false;
    }

    /**
     * Method to add a bread to the oven
     */
    public void addBread(){
        this.bread = new Bread();
        this.occupied = true;
    }

}
