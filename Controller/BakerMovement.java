package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

import Model.Bakery;
import Model.Tile;


/**
 * KeyListener to move the player according to the keys pressed
 * Either left, right, up, down with the arrow keys
 * OR ZQSD (Azerty gaming controls)
 */
public class BakerMovement implements KeyListener {

    /**************
     * ATTRIBUTES *
    * *************/
    private Bakery bakery;
    /***************
     * CONSTRUCTOR *
     * *************/
    public BakerMovement(Bakery m) {
        bakery = m;
    }


    /**************
     *   METHODS  *
     * ************/

    /**
     * Moves the player according to the key pressed
     * The move() method of the player is called and is the one that checks if
     * the move is possible (oven or accessible), and if so, moves the player
     * by changing their position
     *
     * @param e : the key pressed
     **/
    public void keyPressed(KeyEvent e) {

        Tile pos = bakery.getPlayer().getPosition();
        Tile des = pos;

        //Up or Z : moves the player to the tile above
        if (  (e.getKeyCode() == 38 || e.getKeyCode() == 90)
                && pos.getY() > 0  ) { //checks if the player is still in the map
            System.out.println("GO UP");

            des = bakery.getMap()[pos.getX()][pos.getY()-1]; //will attempt to move upwards

        }

        //Down or S : moves the player to the tile below
        else if (  (e.getKeyCode() == 40 || e.getKeyCode() == 83)
                && pos.getY() < bakery.getMap().length -1  ) { //checks if the player is still in the map
            System.out.println("GO DOWN");
            des =bakery.getMap()[pos.getX()][pos.getY()+1]; //will attempt to move downwards
        }

        //Left or Q : moves the player to the tile on the left
        else if (  (e.getKeyCode() == 37 || e.getKeyCode() == 81)
                && pos.getX() > 0  ) { //checks if the player is still in the map
            System.out.println("GO LEFT");
            des = bakery.getMap()[pos.getX()-1][pos.getY()]; //will attempt to move left
        }

        //Right or D : moves the player to the tile on the right
        else if (  (e.getKeyCode() == 39 || e.getKeyCode() == 68)
                && pos.getX() < bakery.getMap()[0].length - 1  ) { //checks if the player is still in the map
            System.out.println("GO RIGHT");
            des = bakery.getMap()[pos.getX()+1][pos.getY()]; //will attempt to move right
        }
        System.out.println("Moving ");
        bakery.getPlayer().move(des); //moves the player to the new tile

    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

}

