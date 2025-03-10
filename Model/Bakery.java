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
    private Baker joueur;
    private Raccoon[] raccoons;

    /****************
     *    GETTERS   *
     ****************/
    public Tile[][] getCarte() { return carte; }
    public Baker getPlayer() { return joueur; }
    public Raccoon[] getRaccoons() { return raccoons; }

    /********************
     *    CONSTRUCTOR   *
     ********************/

    public Bakery(){
        //Pour l'instant, on crée une carte de 5x5 avec un joueur en haut à gauche
        // CHANGER POUR PLUS TARD MAIS LA JE VEUX PAS QUE CA FASSE DES ERREURS
        this.carte = new Tile[8][8];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if(i==0 || i==3){
                    if(j<6){
                        this.carte[i][j] = new Oven(i, j);
                    }
                }
                this.carte[i][j] = new Tile(i, j);
            }
        }
        this.joueur = new Baker(carte[0][0]);
    }


        //Function that finds an unnocupied oven, if there are no unnocupied ovens it returns null and prints a message
        public Oven OvenEmpty(){
            for (int i = 0; i < 4; i+=3){
                for (int j = 0; j < 6; j++){
                    if(carte[i][j] instanceof Oven){
                        Oven o = (Oven)carte[i][j];
                        if(o.isOccupied()){
                            return o;
                        }
                    }
                }
            }
            System.out.println("No oven empty");
            return null;
        }
    
        //Function that finds the closest oven with a ready bread the tile given as argument
        public Oven closestReadyBread(Tile t){
            int x = t.getX();
            int y = t.getY();
            int min = 100;
            Oven oven = null;
            for (int i = 0; i < 4; i+=3){
                for (int j = 0; j < 6; j++){
                    if(carte[i][j] instanceof Oven){
                        Oven o = (Oven)carte[i][j];
                        if(o.isOccupied() && o.getBread().isCooked()){
                            int dist = Math.abs(x-i) + Math.abs(y-j);
                            if(dist < min){
                                min = dist;
                                oven = o;
                            }
                        }
                    }
                }
            }
            return oven;
        }
    
        //Function that gives you a random neighbouring tile you can move to
        //if no neighbouring tile is accessible it returns the tile itself
        public Tile randomNeighbour(Tile t){
            int x = t.getX();
            int y = t.getY();
            for(int i = -1; i<2 ;i++){
                for(int j=-1; j<2; j++){
                    if(x+i<8 && y+j<8 && carte[(x+i)][(y+j)].isAccessible()){
                        return carte[(x+i)][(y+j)];
                    }
                }
            }
            return t;
        }
}
