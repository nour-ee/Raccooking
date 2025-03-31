package Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Bakery;
import Model.Raccoon;
import Model.Tile;
import View.RaccoonPanel;

public class RaccoonMovement extends Thread {
    int DELAY = 1000;
    private Bakery map;
    private ArrayList<JLabel> raccoonPanels;

    /***************
     * CONSTRUCTOR *
     **************/
    public RaccoonMovement(Bakery m, ArrayList<JLabel> rP) {
        this.map = m;
        this.raccoonPanels = rP;
    }

    /**
     * Function that will move the raccoons and increment their age
     */
    public void run(){
        while(true){
            Raccoon[] r = map.getRaccoons();
            for(int i =0; i<r.length; i++){
                if(r[i].getAge() < Raccoon.MAX_AGE){
                    Tile c = r[i].nextMove();
                    r[i].move(c);
                    //(new MovingAnimation(r[i].getLabel(), c)).start();
                    r[i].increment();
                }
            }
            try { Thread.sleep(DELAY); }
            catch (Exception e) { e.printStackTrace(); }
        }
        
    }
}
