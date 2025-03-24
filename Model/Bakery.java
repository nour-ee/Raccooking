package Model;

import javax.swing.tree.FixedHeightLayoutCache;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
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
    public static int BAKERY_H = 10; //height of the grid/bakery
    public static int BAKERY_W = 10;


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

        for (int i = 0; i < BAKERY_W; i++){     //lines = y

            for (int j = 0; j < BAKERY_H; j++){ //columns = x

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
    }


    public Bakery(String filename){
        try {
            Scanner file = new Scanner(new FileInputStream(filename));
            BAKERY_H = file.nextInt();
            BAKERY_W= file.nextInt();
            file.nextLine();
            int goal= file.nextInt();
            int racoonsNb = file.nextInt();

            file.nextLine();
            //CaseTraversable[] var6 = new CaseTraversable[1];
            this.map = new Tile[BAKERY_H][BAKERY_W];
            this.raccoons= new Raccoon[racoonsNb];
            this.ovens = new ArrayList<>();

            int r=0; //index of raccoons TODO : change to ArrayList ---------------------------------------------

            for(int i = 0; i < this.BAKERY_H; i++) {
                String line = file.nextLine();
                for(int j = 0; j < this.BAKERY_W; j++) {
                    Character c = line.charAt(j);
                    Tile t;
                    switch (c) {
                        case 'B':
                            t = new Tile(i,j);
                            Baker baker = new Baker(t);
                            this.player = baker;
                            break;
                        case 'O':
                            t = new Oven(i,j);
                            this.ovens.add((Oven) t);
                            break;
                        case 'R' :
                            t = new Tile(i,j);
                            Raccoon raccoon = new Raccoon(t, this);
                            raccoon.setAge(r);
                            this.raccoons[r]=raccoon;
                            System.out.println("Raccoon "+r+" created at "+i+" "+j);
                            r++;
                            break;
                        default:
                            t = new Tile(i,j);
                    }
                    this.map[i][j] = (Tile) t;
                }
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
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
        for (Oven o : ovens){
            if(!o.isOccupied()){
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }

    /**
     * Method that checks if the tile given in argument is above the player
     * @param t the tile to check
     * @return an optional containing the tile if it is above the player, empty otherwise
     */
    public boolean isAbovePlayer(Tile t){
        return t.getY() == player.getPosition().getY()-1 && t.getX() == player.getPosition().getX();
    }

        /**
         * Method that collects and sells cooked breads from the ovens and removes burnt ones
         */
    public void collectBread(){
        for (Oven o : ovens){
            if(o.isOccupied()){
                if (o.getBread().getState()== Bread.State.COOKED && isAbovePlayer(o)) {
                    player.sellBread();
                    o.removeBread();
                }
                else if (o.getBread().getState()== Bread.State.BURNT) {
                    o.removeBread();
                }
            }
        }
    }
}
