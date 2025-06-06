package Controller;

import Model.Tile;
import View.Display;


import javax.swing.JLabel;
/**
 * Class to manage the animation of the raccoon
 * */
public class MovingAnimation extends Thread {

    /****************
     *  ATTRIBUTES  *
     ****************/
    private JLabel jlabel; //stores the image of the entity that's moving
    private int current_x; //the x to move from
    private int current_y; //the x to move from
    private int steps = 15; //number of steps to move from point a to b 
    private int dx; //movement along x axis during 1 step
    private int dy; //movement along y axis during 1 step
    private int delay = RaccoonMovement.DELAY_RACCOONMOVEMENT/steps; //delay between each step

    /*****************
     *  CONSTRUCTOR  *
     *****************/
    public MovingAnimation(JLabel jp, Tile destination, Tile current) {
        this.jlabel = jp;
        this.current_x = current.getX()*Display.TILE_SIZE;
        this.current_y = current.getY()*Display.TILE_SIZE;
        this.dx = (destination.getX()*Display.TILE_SIZE - current.getX()*Display.TILE_SIZE) / steps;
        this.dy = (destination.getY()*Display.TILE_SIZE - current.getY()*Display.TILE_SIZE) / steps;
    }

    /************
     *  METHOD  *
     ************/
    public void run(){
        for(int i =0; i<steps ; i++){
            current_x += dx;
            current_y += dy;
            jlabel.setBounds(current_x, current_y, Display.TILE_SIZE, Display.TILE_SIZE);
            try { Thread.sleep(delay); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }

}
