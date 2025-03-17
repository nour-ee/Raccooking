package Controller;

import Model.*;

public class RaccoonLife extends Thread {
    int DELAY = 100;
    private Bakery map;

    /***************
     * CONSTRUCTOR *
     **************/
    public RaccoonLife(Bakery m){
        this.map = m;
    }

    /**
     * Function that will check if the raccoons are still alive
     * If they are not, they will be removed from the map and a new one will be created
     */
    public void run(){
        while(true){
            map.checkRaccoons();
            try { Thread.sleep(DELAY); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
