package Model.Threads;


import Model.BakedGoods;

public class Cooking extends Thread{
    private BakedGoods bakedGoods;

    private boolean out;

    /****************
     * CONSTRUCTOR  *
     ****************/
    public Cooking(BakedGoods b){
        this.bakedGoods = b;
        this.out = false;
    }


    /****************
     *   METHODS    *
     ****************/

    public void in(){
        out = true;
    }

    /**
     * Method to simulate the cooking of the bread
     */
    @Override
    public void run() {
        while (bakedGoods.getTime()<= bakedGoods.getT_burnt() && !out) {
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (Exception e) {

            }
            if (bakedGoods.getTime() == bakedGoods.getT_cooked()) {
                System.out.println("Bread is cooked");
                bakedGoods.setState(BakedGoods.State.COOKED);
            } else if (bakedGoods.getTime() == bakedGoods.getT_burnt()) {
                System.out.println("Bread is burnt");
                bakedGoods.setState(BakedGoods.State.BURNT);
            }
            bakedGoods.incrTime(); 
        }
    }



}