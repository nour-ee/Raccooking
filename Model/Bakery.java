package Model;

import java.util.ArrayList;
import java.util.Optional;

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
    public static final int NB_RACCOONS = 5; //number of raccoons
    public static final int BAKERY_H = 10;
    public static final int BAKERY_W = 10;


    /***************
     *  ATTRIBUTES *
     ***************/
    private Tile[][] map;
    private Baker player;
    private Raccoon[] raccoons; //TODO : change to ArrayList ---------------------------------------------
    private ArrayList<Oven> ovens;


    /****************
     *    GETTERS   *
     ****************/
    public Tile[][] getMap() { return map; }
    public Baker getPlayer() { return player; }
    public Raccoon[] getRaccoons() { return raccoons; }
    public ArrayList<Oven> getOvens() {
        return ovens;
    }

    /********************
     *    CONSTRUCTOR   *
     ********************/

    public Bakery(){
        // Initialisation of the bakery
        this.map = new Tile[BAKERY_H][BAKERY_W];
        this.ovens = new ArrayList<>();

        for (int i = 0; i < BAKERY_H; i++){

            for (int j = 0; j < BAKERY_W; j++){
                //Ovens are placed betwen (0,0) and (0,5) , and between (3,0) and (3,5)
                if(i==0 || i==3){
                    if(j<6){
                        Oven o = new Oven(j, i);
                        this.map[i][j] = o;
                        this.ovens.add(o);
                    }
                    else this.map[i][j] = new Tile(j, i);
                }
                else this.map[i][j] = new Tile(j, i);

            }
        }
        //Initialisation of player : as of now, placed in top left corner aka (1,0)
        this.player = new Baker(map[0][1]);
        //Initialisation of the raccoons
        this.raccoons = new Raccoon[NB_RACCOONS];
        for(int i = 0; i<NB_RACCOONS; i++){
            //raccoons[i] = new Raccoon(carte[BAKERY_H-1][BAKERY_W-1], this); //ils sont tous au même endroit ???
            //proposition de placement des raccoons pour tester leur affichage
            int p = (int)(Math.random()*5);
            //place raccoons randomly with verifications that they are placed on accessible tiles
            if(map[i+p][i+1].isAccessible()){
                raccoons[i] = new Raccoon(map[i+p][i+1], this);
            }
            else{
                raccoons[i] = new Raccoon(map[0][8], this);
            }
            raccoons[i].setAge(i);
        }

        for (Tile[] lig : map) {
            for (Tile t : lig) {
                System.out.print(t.hasOven() + " ");
            }
            System.out.println();
        }
    }


    /**
     * Function that returns an oven that is empty
     * @return an oven that is empty, or if none is empty, returns null
     */
    public Oven OvenEmpty(){
        for (int i = 0; i < 4; i+=3){
            for (int j = 0; j < 6; j++){
                if(map[i][j] instanceof Oven){
                    Oven o = (Oven) map[i][j];
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
                if(map[i][j] instanceof Oven){
                    Oven o = (Oven) map[i][j];
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
                if(x+i<8 && y+j<8 && map[(x+i)][(y+j)].isAccessible()){
                    return map[(x+i)][(y+j)];
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
                raccoons[i] = new Raccoon(map[7][7], this);
            }
        }
    }

    /**
     * Method that checks if there is an empty oven
     * @return an optional containing the oven if there is an empty oven, empty otherwise
     */
    public Optional<Oven> hasFreeOven(){
        for (int i = 0; i < 4; i+=3){
            for (int j = 0; j < 6; j++){
                if(map[i][j] instanceof Oven){
                    Oven o = (Oven) map[i][j];
                    if(!o.isOccupied()){
                        return Optional.of(o);
                    }
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Method that collects and sells cooked breads from the ovens and removes burnt ones
     */
    public void collectBread(){
        for (Oven o : ovens){
            if(o.isOccupied()&& o.getBread().getState()!= Bread.State.COOKING){
                if (o.getBread().getState()== Bread.State.COOKED) {
                    player.sellBread();
                }
                o.removeBread();
            }
        }
    }
}
