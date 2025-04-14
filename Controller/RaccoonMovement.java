package Controller;

import java.util.ArrayList;

import javax.swing.JLabel;
import Model.Bakery;
import Model.Raccoon;
import Model.Tile;
/**
 * Class to manage the raccoon movement
 * It will move the raccoons and increment their age
 **/
public class RaccoonMovement extends Thread {

    /****************
     *  ATTRIBUTES  *
     ****************/
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
                if(r[i].getAge() <= Raccoon.MAX_AGE){
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
