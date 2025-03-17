package Model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.random.RandomGenerator;

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
    public static final int NB_RACCOONS = 5; //number of raccoons on the map (might change in later versions)
    public static final int BAKERY_H = 10; //height of the grid/bakery
    public static final int BAKERY_W = 10;


    /***************
     *  ATTRIBUTES *
     ***************/
    private Tile[][] map;
    private Baker player;
    private Raccoon[] raccoons; //TODO : change to ArrayList ---------------------------------------------
    private ArrayList<Oven> ovens; //ovens to cook breads


    /****************
     *    GETTERS   *
     ****************/
    public Tile[][] getMap() { return map; }
    public Baker getPlayer() { return player; }
    public Raccoon[] getRaccoons() { return raccoons; }
    public ArrayList<Oven> getOvens() { return ovens; }

    /********************
     *    CONSTRUCTOR   *
     ********************/
    public Bakery(){
        // Initialisation of the bakery
        this.map = new Tile[BAKERY_W][BAKERY_H];
        this.ovens = new ArrayList<>();

        for (int i = 0; i < BAKERY_W; i++){     //lines

            for (int j = 0; j < BAKERY_H; j++){ //columns

                //Ovens are placed between (0,0) and (5,0) , and between (0,3) and (5,3)
                if((j==0 || j==3)&&i<6){
                    Oven o = new Oven(i, j);
                    this.map[i][j] = o;
                    this.ovens.add(o);
                }
                else this.map[i][j] = new Tile(i, j);

            }
        }

        //Initialization of player : as of now, placed in top left corner aka (0,1)
        this.player = new Baker(map[0][1]);

        //Initialization of the raccoons
        this.raccoons = new Raccoon[NB_RACCOONS];
        for(int i = 0; i<NB_RACCOONS; i++){
            raccoons[i] = new Raccoon(map[8][i*2], this);
            raccoons[i].setAge(i);
        }

        for (Tile[] lig : map) {
            for (Tile t : lig) {
                System.out.print(t.hasRacoon() + " ");
            }
            System.out.println();
        }
    }


    /***************
     *  METHODS    *
     ***************/
    
    /**
     * Function that returns a random neighbour of the tile given in parameter
     * @param t the tile from which we are looking for a neighbour
     * @return a random neighbour of the tile, or the tile itself if no neighbour is accessible
     */
    public Tile randomNeighbour(Tile t){
        int x = t.getX();
        int y = t.getY();
        ArrayList<Tile> neighbours = new ArrayList<>();
        for(int i =-1; i<2 ;i++){
            for(int j = -1; j<2; j++){
                if(x+i>=0 && x+i<BAKERY_W && y+j>=0 && y+j<BAKERY_H){
                    Tile c = map[x+i][y+j];
                    if(c.isAccessible()&&c.isEmpty()){
                        neighbours.add(c);
                    }
                }
            }
        }
        if(neighbours.size() == 0) return t;
        return neighbours.get(Random.from(RandomGenerator.getDefault()).nextInt(neighbours.size())); 
    }

    /**
     * Method that goes through the raccoons and checks if any have died (age>20)
     * If yes, removes them from the array and replaces them with a new raccoon
     */
    public void checkRaccoons(){
        for(int i = 0; i<raccoons.length; i++){
            if(raccoons[i].getAge() >= Raccoon.MAX_AGE){
                raccoons[i].getPosition().RacoonHasLeft();
                raccoons[i] = new Raccoon(map[BAKERY_H-1][BAKERY_W-1], this);
            }
        }
    }

    /**
     * Method that checks if there is an empty oven
     * @return an optional containing the oven if there is an empty oven, empty otherwise
     */
    public Optional<Oven> hasFreeOven(){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 4; j+=3){
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
     * Method that checks if there is an empty tile
     * @return an optional containing the tile if there is an empty tile, empty otherwise
     */
    public Optional<Tile> tileAbove(Tile t){
        int x = t.getX();
        int y = t.getY();
        if(y>0){
            Tile c = map[x][y-1];
            if(c.isAccessible()&&c.isEmpty()){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

        /**
         * Method that collects and sells cooked breads from the ovens and removes burnt ones
         */
    public void collectBread(){
        Optional<Tile> t =tileAbove(player.getTile());
        if(t.isPresent() && t.get() instanceof Oven){
            Oven o= (Oven) t.get();
            if(o.isOccupied()&& o.getBread().getState()!= Bread.State.COOKING){
                if (o.getBread().getState()== Bread.State.COOKED) {
                    player.sellBread();
                }
                o.removeBread();
            }
        }


        /*
        for (Oven o : ovens){
            if(o.isOccupied()&& o.getBread().getState()!= Bread.State.COOKING){
                if (o.getBread().getState()== Bread.State.COOKED) {
                    player.sellBread();
                }
                o.removeBread();
            }
        }

         */
    }
}
