package Model;

import java.awt.*;

import Controller.Cooking;

public class Bread {
    public enum State {
        COOKING, COOKED, BURNT
    }
    private State state ;

    private Cooking cooking;

    private int time;
    public static final int COOKED=100;
    public static final int BURNT=150;

    public static final int PRICE = 20;

    public Bread() {
        this.state = State.COOKING;
        time=0;
        cooking= new Cooking(this);
        cooking.start();
    }

    public void setState(State s){ state = s;}

    public State getState() {
        return state;
    }

    public int getTime() {
        return time;
    }

    public void incrTime(){time++;}

    public boolean isCooked(){return state== State.COOKED;}


}