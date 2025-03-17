package Controller;

import Model.Bakery;
import Model.Raccoon;
import View.RaccoonPanel;

public class RaccoonMovement extends Thread {
    int DELAY = 100;
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
        Raccoon[] r = map.getRaccoons();
        for(int i =0; i<r.length; i++){
            while(r[i].getAge() < 10){
                r[i].increment();
                r[i].move(map.randomNeighbour(r[i].getPosition()));
            }
        
        }
        try { Thread.sleep(DELAY); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
