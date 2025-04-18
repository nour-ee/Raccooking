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
    public BakedGoods getBakedGoods() {
        return bakedGoods;
    }
    public boolean isOccupied() {
        return occupied;
    }


    /****************
    * CONSTRUCTOR   *
    *****************/
    public Oven(int x, int y, int id){
        super(x, y);
        this.accessible = false;
        this.occupied = false;
        this.hasOven=true;
    }

    /**
     * Method to remove the bread from the oven
     */
    public void removeBakedGoods(){
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
}
