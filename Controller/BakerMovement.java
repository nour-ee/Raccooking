package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

        //Up or Z : moves the player to the tile above
        if (  (e.getKeyCode() == 38 || e.getKeyCode() == 90)
                && bakery.getPlayer().getPosition().getY() > 0  ) { //checks if the player is still in the map
            //bakery.getPlayer().move(bakery.getMap()[bakery.getPlayer().getPosition().getX()][bakery.getPlayer().getPosition().getY()-1]);)
            System.out.println("GO UP");
            bakery.getPlayer().move(bakery.getMap()[pos.getX()][pos.getY()-1]); //will attempt to move upwards
        }

        //Down or S : moves the player to the tile below
        if (  (e.getKeyCode() == 40 || e.getKeyCode() == 83)
                && bakery.getPlayer().getPosition().getY() < bakery.getMap().length -1  ) { //checks if the player is still in the map
            //bakery.getPlayer().move(bakery.getMap()[bakery.getPlayer().getPosition().getX()][bakery.getPlayer().getPosition().getY()+1]);
            System.out.println("GO DOWN");
            bakery.getPlayer().move(bakery.getMap()[pos.getX()][pos.getY()+1]); //will attempt to move downwards
        }

        //Left or Q : moves the player to the tile on the left
        if (  (e.getKeyCode() == 37 || e.getKeyCode() == 81)
                && bakery.getPlayer().getPosition().getX() > 0  ) { //checks if the player is still in the map
            //bakery.getPlayer().move(bakery.getMap()[bakery.getPlayer().getPosition().getX()-1][bakery.getPlayer().getPosition().getX()]);
            System.out.println("GO LEFT");
            bakery.getPlayer().move(bakery.getMap()[pos.getX()-1][pos.getY()]); //will attempt to move left
        }

        //Right or D : moves the player to the tile on the right
        if (  (e.getKeyCode() == 39 || e.getKeyCode() == 68)
                && bakery.getPlayer().getPosition().getX() < bakery.getMap()[0].length - 1  ) { //checks if the player is still in the map
            //bakery.getPlayer().move(bakery.getMap()[bakery.getPlayer().getPosition().getX()+1][bakery.getPlayer().getPosition().getY()]);
            System.out.println("GO RIGHT");
            bakery.getPlayer().move(bakery.getMap()[pos.getX()+1][pos.getY()]); //will attempt to move right
        }

    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

}

