package Model;

/**
 * Classe qui représente la boulangerie
 * aka la carte du jeu :
 * Contient un tableau 2D de cases, un joueur
 * et un tableau de ratons laveurs
 * (pour l'instant)
 */
public class Bakery {
    /***************
    *  CONSTANTS   *
    ****************/
    public static final int SIZE = 8; //size of the grid


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
        // Initialisation of the bakery
        this.carte = new Tile[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                //Ovens are placed betwen (0,0) and (0,5) as well as (3,0) and (3,5)
                if(i==0 || i==3){
                    if(j<6){
                        this.carte[i][j] = new Oven(i, j);
                    }
                }
                this.carte[i][j] = new Tile(i, j);
            }
        }
        //Initialisation of the player, placed for the moment at (0,0) i.e the top left corner
        this.joueur = new Baker(carte[0][0]);
    }


    /**
     * Function that returns an oven that is empty
     * @return an oven that is empty, or if none is empty, returns null
     */
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

    /**
     * Function that finds the closest oven with a cooked bread
     * @param t the tile from which we are looking for the oven
     * @return the oven with a cooked bread that is the closest to the tile
     */
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
    
    /**
     * Function that returns a random neighbour of the tile given in parameter
     * @param t the tile from which we are looking for a neighbour
     * @return a random neighbour of the tile, or the tile itself if no neighbour is accessible
     */
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


    /**
     * Method that goes through the raccoons checks if any have died (age>20)
     * if yes they are removed from the array and replaced by a new raccoon
     */
    public void checkRaccoons(){
        for(int i = 0; i<raccoons.length; i++){
            if(raccoons[i].getAge() > 20){
                raccoons[i] = new Raccoon(carte[7][7], this);
            }
        }
    }
}
