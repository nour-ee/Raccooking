package Model;

import Controller.Cooking;

public class Bread {
    // Enum to represent the state of the bread
    public enum State {
        COOKING, COOKED, BURNT
    }

    /****************
     *  ATTRIBUTES  *
     ****************/
    private State state ;
    private Cooking cooking;
    private int time;
    public static final int T_COOKED =150;
    public static final int T_BURNT =250;
    public static final int PRICE = 20; //selling price of a bread


    /****************
     *  CONSTRUCTOR  *
     ****************/
    public Bread() {
        this.state = State.COOKING;
        time=0;
        cooking= new Cooking(this);
        cooking.start();
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