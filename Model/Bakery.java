package Model;

import Controller.RaccoonLife;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Class that represents the bakery
 * aka the game map:
 * contains a 2D table of Tiles, a player
 * and a table of raccoons
 */

public class Bakery {
    /***************
    *  CONSTANTS   *
    ****************/
    public static final int NB_RACCOONS = 5; //number of raccoons on the map (might change in later versions)
    public static int BAKERY_H = 10; //height of the grid/bakery
    public static int BAKERY_W = 10;
    public static boolean endOfGame = false;
    public static int GOAL; //monetary goal of the game


    /***************
     *  ATTRIBUTES *
     ***************/
    private Tile[][] map;
    private Baker player;
    private Raccoon[] raccoons; //raccoons on the map
    private ArrayList<Oven> ovens; //ovens to cook breads


    /****************
     *    GETTERS   *
     ****************/
    public Tile[][] getMap() { return map; }
    public Baker getPlayer() { return player; }
    public Raccoon[] getRaccoons() { return raccoons; }
    public ArrayList<Oven> getOvens() { return ovens; }
    //public String getLevelFile() { return levelFile; }

    /****************
     *    SETTERS   *
     ****************/
    //public void setLevel(int level) { this.levelFile = "levels/level"+level+".txt"; }

    /********************
     *    CONSTRUCTOR   *
     ********************/

    public Bakery(String filename){
        try {
            Scanner file = new Scanner(new FileInputStream(filename));
            BAKERY_H = file.nextInt();
            BAKERY_W= file.nextInt();
            file.nextLine();
            GOAL= file.nextInt();
            int racoonsNb = file.nextInt();

            file.nextLine();
            //CaseTraversable[] var6 = new CaseTraversable[1];
            this.map = new Tile[BAKERY_H][BAKERY_W];
            this.raccoons= new Raccoon[racoonsNb];
            this.ovens = new ArrayList<>();

            int r=0; //raccoon index
            int nbOvens = 0;
            for(int i = 0; i < Bakery.BAKERY_H; i++) {
                String line = file.nextLine();
                for(int j = 0; j < Bakery.BAKERY_W; j++) {
                    Character c = line.charAt(j);
                    Tile t;
                    switch (c) {
                        case 'B':
                            t = new Tile(i,j);
                            Baker baker = new Baker(t, this);
                            this.player = baker;
                            break;
                        case 'O':
                            t = new Oven(i,j,nbOvens);
                            this.ovens.add((Oven) t);
                            nbOvens++;
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


        //Starting the threads
        RaccoonLife rl = new RaccoonLife(this);
        rl.start();

        //make the tiles next to new baker pos have a nextToBaker
        for(Tile t : this.neighbours(this.getPlayer().position)){
            t.setNextToBaker(true);
        }
    }


    /***************
     *  METHODS    *
     ***************/

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
     * Method that checks if a tile is collectible by the player (is one tile above, below, left or right of the player)
     * @param t the tile to check
     * @return true if the tile is collectible, false otherwise
     */
    public boolean isCollectible(Tile t){
        return Math.abs(t.getY() - player.getPosition().getY())==1  && t.getX()==player.getPosition().getX() ||
                Math.abs(t.getX() - player.getPosition().getX())==1  && t.getY()==player.getPosition().getY();
    }

    /**
     * Method that collects and sells cooked breads from the ovens and removes burnt ones
     */
    public void collect(){
        for (Oven o : ovens){
            if(o.isOccupied()){
                if (o.getBakedGoods().getState()== BakedGoods.State.COOKED && isCollectible(o)) {
                    player.sellBread(o.getBakedGoods().getPrice());
                    o.removeBakedGoods();
                }
                else if (o.getBakedGoods().getState()== BakedGoods.State.BURNT) {
                    o.removeBakedGoods();
                }
            }
        }
    }

    /**
    * Methods that returns the raccoon that are next to a tile given in argument
    * @param t the tile from which we are looking for raccoons
    * @return an array of raccoons that are next to the tile
    */
    public Raccoon[] raccoonsNextTo(Tile t){
        ArrayList<Raccoon> r = new ArrayList<>();
        for (Raccoon raccoon : raccoons) {
            if (raccoon.getPosition().isNeighbour(t)) {
                r.add(raccoon);
            }
        }
        return r.toArray(new Raccoon[0]);
    }

    /**
     * Method that returns the neighbours of a tile
     * @param t the tile from which we are looking for neighbours
     * @return an array of tiles that are neighbours of the tile
     */
    public Tile[] neighbours(Tile t){
        ArrayList<Tile> n = new ArrayList<>();
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (t.getX()+i >= 0 && t.getX()+i < BAKERY_W && t.getY()+j >= 0 && t.getY()+j < BAKERY_H){
                    Tile c = map[t.getX()+i][t.getY()+j];
                    n.add(c);
                }
            }
        }
        return n.toArray(new Tile[0]);
    }


    /**
     * Method that checks if all ovens are empty
     * @return true if all ovens are empty, false otherwise
     */
    public boolean allOvensEmpty() {
        for (Oven o : ovens) {
            if (o.isOccupied()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if the game has ended
     */
    public void gameEnded(){
        int money=player.getMoney();
        if(money>=GOAL){
            endOfGame = true;
            System.out.println("You won!");
        }
        else if (money<=0 && allOvensEmpty()){
            endOfGame = true;
           System.out.println("You lost!");
        }
    }
}
