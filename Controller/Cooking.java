package Controller;


import Model.Bread;

import static Model.Bread.T_BURNT;
import static Model.Bread.T_COOKED;

public class Cooking extends Thread{
    private Bread bread;

    /****************
     * CONSTRUCTOR  *
     ****************/
    public Cooking(Bread b){
        this.bread = b;
    }


    /****************
     *   METHODS    *
     ****************/

    /**
     * Method to simulate the cooking of the bread
     */
    @Override
    public void run() {
        while (bread.getTime()<= T_BURNT) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            if (bread.getTime() == T_COOKED) {
                System.out.println("Bread is cooked");
                bread.setState(Bread.State.COOKED);
            } else if (bread.getTime() == T_BURNT) {
                System.out.println("Bread is burnt");
                bread.setState(Bread.State.BURNT);
            }
            bread.incrTime();
        }
    }



}