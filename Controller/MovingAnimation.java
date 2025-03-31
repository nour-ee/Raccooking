package Controller;

import Model.Tile;
import java.awt.*;

public class MovingAnimation extends Thread {

    /****************
     *  ATTRIBUTES  *
     ****************/
    private Label entityImage; //stores the image of the entity that's moving
    private Tile destination; //the tile to move to

    /*****************
     *  CONSTRUCTOR  *
     *****************/
    public MovingAnimation(Label entityImage, Tile destination) {
        this.entityImage = entityImage;
        this.destination = destination;
    }

    /************
     *  METHOD  *
     ************/
    public void run(){
        //j
    }

}
