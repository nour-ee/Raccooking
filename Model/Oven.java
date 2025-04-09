package Model;

public class Oven extends Tile {
    /****************
    *  ATTRIBUTES  *
    ****************/
    private BakedGoods bakedGoods; // the bread in the oven, when occupied null the rest of the time
    private boolean occupied; // indicates if the oven is occupied

    /****************
    *   GETTERS   *
    ***************/
    public BakedGoods getBread() {
        return bakedGoods;
    }
    public boolean isOccupied() {
        return occupied;
    }

    /****************
    *  SETTERS   *
    **************/
    public void setBread(BakedGoods bakedGoods) {
        this.bakedGoods = bakedGoods;
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
        this.bakedGoods= new BakedGoods();
    }

    /**
     * Method to remove the bread from the oven
     */
    public void removeBread(){
        this.bakedGoods.stopThread();
        this.bakedGoods = null; //the bread still exists even though it's not contained anywhere
        this.occupied = false;
    }

    /**
     * Method to add a bread to the oven
     */
    public void addBakedGoods(String type){
        switch (type){
            case "Bread" -> {
                this.bakedGoods = new Bread();
                break;
            }
            case "Croissant" ->{
                this.bakedGoods = new Croissant();
                break;
            }
            case "Brioche" -> {
                this.bakedGoods = new Brioche();
                break;
            }

        }
        this.occupied = true;
    }

    /**
     * Method to add a croissant to the oven
     */
    public void addCroissant() {
        this.bakedGoods = new Croissant();
        this.occupied = true;
    }

    /**
     * Method to add a Brioche to the oven
     */
    public void addBrioche() {
        this.bakedGoods = new Brioche();
        this.occupied = true;
    }
}
