package Model;


import java.awt.*;
import java.util.ArrayList;

import static Model.Bread.BURNT;
import static Model.Bread.COOKED;

public class Cooking extends Thread{
    private Bread bread;

    public Cooking(Bread b){
        this.bread = b;
    }


    @Override
    public void run() {
        while (bread.getTime()<=BURNT) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            if (bread.getTime() == 0) {
                System.out.println("Bread is cooking");}
            if (bread.getTime() == COOKED) {
                bread.changeState();
                System.out.println("Bread is cooked");
            } else if (bread.getTime() == BURNT) {
                bread.changeState();
                System.out.println("Bread is burnt");
            }
            bread.incrTime();
        }
    }



}