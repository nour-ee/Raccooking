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
    private Tile[][] map;
    private Baker player;

    //private Raccoon array raccoons
    private int lostBread; // number of breads stolen by raccoons

    public static final int BAKERY_H = 10;
    public static final int BAKERY_W = 10;

    /****************
     *    GETTERS   *
     ****************/
    public Tile[][] getMap() { return map; }
    public Baker getPlayer() { return player; }

    /********************
     *    CONSTRUCTOR   *
     ********************/

    public Bakery(){
        //Pour l'instant, on crée une carte de 5x5 avec un joueur en haut à gauche
        // CHANGER POUR PLUS TARD MAIS LA JE VEUX PAS QUE CA FASSE DES ERREURS
        this.map = new Tile[BAKERY_H][BAKERY_W];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                this.map[i][j] = new Tile(i, j);
            }
        }
        this.player = new Baker(map[0][0]);
    }

}
