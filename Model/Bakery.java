package Model;

/**
 * Classe qui représente la boulangerie
 * aka la carte du jeu :
 * Contient un tableau 2D de cases, un joueur
 * et un tableau de ratons laveurs
 * (pour l'instant)
 */
public class Bakery {

    /****************
     *  ATTRIBUTES  *
     ****************/
    private Tile[][] carte;
    private Player joueur;

    //private Raccoon array raccoons

    /****************
     *    GETTERS   *
     ****************/
    public Tile[][] getCarte() { return carte; }
    public Player getJoueur() { return joueur; }

    /********************
     *    CONSTRUCTOR   *
     ********************/

    public Bakery(){
        //Pour l'instant, on crée une carte de 5x5 avec un joueur en haut à gauche
        // CHANGER POUR PLUS TARD MAIS LA JE VEUX PAS QUE CA FASSE DES ERREURS
        this.carte = new Tile[5][5];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                this.carte[i][j] = new Tile(i, j);
            }
        }
        this.joueur = new Player(carte[0][0]);
    }

}
