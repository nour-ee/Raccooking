package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.Bakery;


/**
 * Classe qui récupère les touches du clavier pour appeler le déplacement du joueur
 * On utilise les flèches directionnelles pour déplacer le joueur
 * OU on utilise ZQSD pour déplacer le joueur (pour les gamers sur clavier AZERTY)
 *
 * RETAPER EN PLUS JOLI LA C'EST MOCHE MAIS JE SUIS CREVEE FLEMME
 */
public class BakerMovement implements KeyListener {

    private Bakery bakery;

    public BakerMovement(Bakery m) {
        bakery = m;
    }

    public void keyPressed(KeyEvent e) {

        //Up or Z : déplace le joueur vers la case du dessus
        if (  (e.getKeyCode() == 38 || e.getKeyCode() == 90)
                && bakery.getPlayer().getPosition().getY() > 0  ) { //vérifie que le joueur reste dans la carte
            bakery.getPlayer().move(bakery.getCarte()[bakery.getPlayer().getPosition().getX()][bakery.getPlayer().getPosition().getY()-1]);
        }

        //Down or S : déplace le joueur vers la case du dessous
        if (  (e.getKeyCode() == 40 || e.getKeyCode() == 83)
                && bakery.getPlayer().getPosition().getY() < bakery.getPlayer().getPosition().getY() - 1  ) { //vérifie que le joueur reste dans la carte
            bakery.getPlayer().move(bakery.getCarte()[bakery.getPlayer().getPosition().getX()][bakery.getPlayer().getPosition().getY()+1]);
        }

        //Left or Q : déplace le joueur vers la case de gauche
        if (  (e.getKeyCode() == 37 || e.getKeyCode() == 81)
                && bakery.getPlayer().getPosition().getX() > 0  ) { //vérifie que le joueur reste dans la carte
            bakery.getPlayer().move(bakery.getCarte()[bakery.getPlayer().getPosition().getX()-1][bakery.getPlayer().getPosition().getX()]);
        }

        //Right or D : déplace le joueur vers la case de droite
        if (  (e.getKeyCode() == 39 || e.getKeyCode() == 68)
                && bakery.getPlayer().getPosition().getX() < bakery.getCarte()[0].length - 1  ) { //vérifie que le joueur reste dans la carte
            bakery.getPlayer().move(bakery.getCarte()[bakery.getPlayer().getPosition().getX()+1][bakery.getPlayer().getPosition().getY()]);
        }

    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

}

