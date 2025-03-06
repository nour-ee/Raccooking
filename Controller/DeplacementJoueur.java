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
public class DeplacementJoueur implements KeyListener {

    private Bakery map;

    public DeplacementJoueur(Bakery m) {
        map = m;
    }

    public void keyPressed(KeyEvent e) {

        //Up or Z : déplace le joueur vers la case du dessus
        if (  (e.getKeyCode() == 38 || e.getKeyCode() == 90)
                && map.getJoueur().getPosition().getY() > 0  ) { //vérifie que le joueur reste dans la carte
            map.getJoueur().deplacer(map.getCarte()[map.getJoueur().getPosition().getX()][map.getJoueur().getPosition().getY()-1]);
        }

        //Down or S : déplace le joueur vers la case du dessous
        if (  (e.getKeyCode() == 40 || e.getKeyCode() == 83)
                && map.getJoueur().getPosition().getY() < map.getJoueur().getPosition().getY() - 1  ) { //vérifie que le joueur reste dans la carte
            map.getJoueur().deplacer(map.getCarte()[map.getJoueur().getPosition().getX()][map.getJoueur().getPosition().getY()+1]);
        }

        //Left or Q : déplace le joueur vers la case de gauche
        if (  (e.getKeyCode() == 37 || e.getKeyCode() == 81)
                && map.getJoueur().getPosition().getX() > 0  ) { //vérifie que le joueur reste dans la carte
            map.getJoueur().deplacer(map.getCarte()[map.getJoueur().getPosition().getX()-1][map.getJoueur().getPosition().getX()]);
        }

        //Right or D : déplace le joueur vers la case de droite
        if (  (e.getKeyCode() == 39 || e.getKeyCode() == 68)
                && map.getJoueur().getPosition().getX() < map.getCarte()[0].length - 1  ) { //vérifie que le joueur reste dans la carte
            map.getJoueur().deplacer(map.getCarte()[map.getJoueur().getPosition().getX()+1][map.getJoueur().getPosition().getY()]);
        }

    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

}

