package Model;

import Controller.Cooking;

public class BakedGoods {
    // Enum to represent the state of the bread
    public enum State {
        NULL, COOKING, COOKED, BURNT
    }

    /****************
     *  ATTRIBUTES  *
     ****************/
    private State state ;
    protected Cooking cooking;
    private int time;
    protected int t_cooked=150;
    protected int  t_burnt =250;
    private int price;//selling price of a bread

    //ingredients necessary to bake this thing
    private int[] ingredients = new int[4]; // 0 flour, 1 egg, 2 yeast, 3 butter


    /****************
     *  CONSTRUCTOR  *
     ****************/
    public BakedGoods(int p,int[] ingr, int t_c, int t_b){
        this.t_cooked = t_c;
        this.t_burnt = t_b;
        this.price = p;
        state = State.NULL;
        ingredients=ingr;
        time=0;
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


}