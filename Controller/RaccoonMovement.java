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
    public static final int DELAY_RACCOONMOVEMENT = 1500;
    private Bakery map;
    private ArrayList<JLabel> raccoonLabels;

    /***************
     * CONSTRUCTOR *
     **************/
    public RaccoonMovement(Bakery m, ArrayList<JLabel> rL) {
        this.map = m;
        this.raccoonLabels = rL;
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
                    (new MovingAnimation(raccoonLabels.get(i), c, r[i].position)).start();
                    r[i].move(c);
                    r[i].increment();
                }
            }
            try { Thread.sleep(DELAY_RACCOONMOVEMENT); }
            catch (Exception e) { e.printStackTrace(); }
        }
        
    }
}
