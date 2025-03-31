package Controller;

import Model.Bakery;
import Model.Raccoon;
import Model.Tile;
import View.RaccoonPanel;

public class RaccoonMovement extends Thread {
    int DELAY = 1000;
    private Bakery map;

    /***************
     * CONSTRUCTOR *
     **************/
    public RaccoonMovement(Bakery m){
        this.map = m;
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
                    r[i].increment();
                }
            }
            try { Thread.sleep(DELAY); }
            catch (Exception e) { e.printStackTrace(); }
        }
        
    }
}
