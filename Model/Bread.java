package Model;

import java.awt.*;

public class Bread {
    public enum State {
        COOKING, COOKED, BURNT
    }
    private State state ;
    private Cooking cooking;

    public static final int PRICE = 20;

    public Bread() {
        this.state = State.COOKING;
        cooking= new Cooking(this);
        cooking.start();
    }

    public void changeState(){
        if (state == State.COOKING){
            state = State.COOKED;
        } else if (state == State.COOKED){
            state = State.BURNT;
        }
    }

    public State getState() {
        return state;
    }

    public int getTime() {
        return cooking.getTime();
    }

    public boolean isCooked(){return state== State.COOKED;}


}
