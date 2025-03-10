package Model;


import java.awt.*;
import java.util.ArrayList;

public class Cooking extends Thread{
    private Bread bread;

    public Cooking(Bread b){
        this.bread = b;
    }

    private int time ;

    public static final int COOKED=100;
    public static final int BURNT=150;

    public int getTime() {
        return time;
    }

    @Override
    public void run() {
        while (time<=BURNT) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            if (time == 0) {
                System.out.println("Bread is cooking");}
            if (time == COOKED) {
                bread.changeState();
                System.out.println("Bread is cooked");
            } else if (time == BURNT) {
                bread.changeState();
                System.out.println("Bread is burnt");
            }
            time++;
        }
    }

    public Bread getBread() {
        return bread;
    }


}

