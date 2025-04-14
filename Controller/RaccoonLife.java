package Controller;

import Model.*;
/** Class to manage the raccoon life
 * It will check if the raccoons are still alive and remove them from the map if they are not
 **/
public class RaccoonLife extends Thread {

    /****************
     *  ATTRIBUTES  *
     ****************/
    int DELAY = 1000;
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
        while( !Bakery.endOfGame ){
            map.checkRaccoons();
            try { Thread.sleep(DELAY); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
