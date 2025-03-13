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
        this.accesible = false;
        this.occupied = true;
        this.bread = new Bread(); //TODO : change this to null quand on en aura plus besoin
        this.hasOven=true;
        //System.out.println("Oven created at (" + x + ", " + y + ")"+hasOven+hasOven());
    }

    public void removeBread(){
        this.bread = null;
        this.occupied = false;
    }

    public void addBread(){
        this.bread = new Bread();
        this.occupied = true;
    }

}
