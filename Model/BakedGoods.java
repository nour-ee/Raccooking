package Model;

import Controller.Cooking;

import java.util.HashMap;

public class BakedGoods {
    // Enum to represent the state of the bread
    public enum State {
        NULL, COOKING, COOKED, BURNT
    }

    /****************
     *  ATTRIBUTES  *
     ****************/
    protected State state ;
    protected Cooking cooking;
    private int time;
    protected int t_cooked=150;
    protected int  t_burnt =300;
    private int price;//selling price of a bread

    //ingredients necessary to bake this thing


    /****************
     *  CONSTRUCTOR  *
     ****************/
    public BakedGoods(int p, int t_c, int t_b){
        this.t_cooked = t_c;
        this.t_burnt = t_b;
        this.price = p;
        state = State.NULL;
        time=0; // time is initialized to 0, seconds
    }

    public BakedGoods(){
        state = State.NULL;
    }


    /**************************
     *   GETTERS & SETTERS    *
     **************************/
    public void setState(State s){ state = s;}

    public State getState() {
        return state;
    }

    public int getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public int getT_cooked() {
        return t_cooked;
    }

    public int getT_burnt() {
        return t_burnt;
    }


    /****************
     *   METHODS    *
     ****************/
    public void incrTime(){time++;}
    public boolean isCooked(){return state== State.COOKED;}

    /**
     * Method to kill the thread
     * when the bread is discarded
     */
    public void stopThread(){
        cooking.in();
    }


    /**
     * Method to get the recipe of the bread
     * @return the recipe of the bread
     */
    public static HashMap<String, Integer> getRecipe(String type) {
        switch (type) {
            case "Bread":
                return Bread.getRecipe();
            case "Croissant":
                return Croissant.getRecipe();
            case "Brioche":
                return Brioche.getRecipe();
            default:
                return null;
        }
    }



}