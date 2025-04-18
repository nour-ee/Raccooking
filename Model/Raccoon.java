package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;
/**
 * Class that represents the raccoon
 * It will manage the position of the raccoon
 * and the number of bread , croissant and brioche it has stolen
 * It will also manage the age of the raccoon
 */
public class Raccoon extends Entity {
    /****************
     *  CONSTANTS   *
     ****************/
    public static int MAX_AGE = 50;
    /****************
     *  ATTRIBUTES  *
     ****************/
    private Bakery bakery ;
    private int age = 0; // correspons to the life of the raccoon
    private int nb_bread; // number of bread stolen by the raccoon
    private int nb_croissant; // number of croissant stolen by the raccoon
    private int nb_brioche; // number of brioche stolen by the raccoon
    private boolean on_the_run = false; // if the baker is within a certain radius, making the raccoon run away

    /****************
     *    GETTERS   *
     ****************/
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Tile getPosition() {
        return position;
    }
    public int getBread() { 
        return nb_bread;
    }
    public int getCroissant() {return nb_croissant;}
    public int getBrioche() {return nb_brioche;}
    public boolean is_on_the_run(){
        return this.on_the_run;
    }
    public void set_on_the_run(boolean b){
        this.on_the_run = b;
    }

    /********************
    *    CONSTRUCTOR   *
    ********************/
    public Raccoon(Tile c, Bakery b){
        this.position = c;
        c.RaccoonArrived();
        this.age = 0;
        this.bakery = b;
        this.nb_bread = 0;
        this.nb_croissant = 0;
        this.nb_brioche = 0;
        this.on_the_run = false;
    }

    /********************
    *      METHODS      *
    ********************/


    /**
     * Method that changes the position of the raccoon
     * @param c the tile to move to
     */
    @Override
    public void move(Tile c){
        this.position.RaccoonHasLeft();
        this.position = c;
        this.position.RaccoonArrived();
        if(!c.isNextToBaker()){
            this.set_on_the_run(false);
        }
    }

    /**
     * Method that increments the age of the raccoon (i.e the number of move it has done)
     */
    public void increment(){
        this.setAge(this.age+1);
    }   

    /**
     * Method that makes the raccoon eat the good
     *@param o the oven from which the raccoon will eat the bread
     */
    public void eatGoods(Oven o){
        switch(o.getBakedGoods().getClass().toString()){
            case "class Model.Bread" ->{
                this.nb_bread+=1;
            }
            case "class Model.Croissant" ->{
                this.nb_croissant+=1;
            }
            case "class Model.Brioche" ->{
                this.nb_brioche+=1;
            }
            default -> {
                System.out.println("bread not eaten");
            }
        }
        o.removeBakedGoods();

    }
    


    /**
     * Method that finds the closest oven with a cooked bread
     * @return the oven with a cooked bread that is the closest to the tile
     */
    public Oven closestReadyBread(){
        Oven oven = null;
        for(Tile t: this.bakery.tilesWithinRadius(this.position, 2)){
            if(t.hasOven()){
                Oven o = (Oven)t;
                if(o.isOccupied() && o.getBakedGoods().isCooked()){
                    oven = o;
                }
            }
        }
        return oven;
    }
    
    /**
     * Method that decides what is the best move to get to the closest bread
     * @param o closest oven
     * @return the tile to move to
     */
    public Tile moveTowardsBread(Oven o){
        if(o.getX()>position.getX()){ //oven is to the right of the raccoon
            if(bakery.getMap()[position.getX()+1][position.getY()].isAccessibleToRaccoon()){
                return bakery.getMap()[position.getX()+1][position.getY()];
            }
            
        }else if (o.getX()<position.getX()){ //oven is to the left of the raccoon
            if(bakery.getMap()[position.getX()-1][position.getY()].isAccessibleToRaccoon()){
                return bakery.getMap()[position.getX()-1][position.getY()];
            }
        }else if(o.getY()-1>position.getY()){ //oven above the raccoon
            if(bakery.getMap()[position.getX()][position.getY()+1].isAccessibleToRaccoon()){
                return bakery.getMap()[position.getX()][position.getY()+1];
            }
        }else if (o.getY()-1<position.getY()) { //oven below the raccoon
            if(bakery.getMap()[position.getX()][position.getY()-1].isAccessibleToRaccoon()){
                return bakery.getMap()[position.getX()][position.getY()-1];
            }
              
        }
        return position; //if no move is possible, return the current position
    }

    /**
     * Function that returns a random neighbour of our raccoon
     * @return a random neighbour of the tile, or the tile itself if no neighbour is accessible
     */
    public Tile randomNeighbour(){
        Tile t = this.position;
        int x = t.getX();
        int y = t.getY();
        ArrayList<Tile> neighbours = new ArrayList<>();
        for(int i =-1; i<2 ;i++){
            for(int j = -1; j<2; j++){
                if(x+i>=0 && x+i<Bakery.BAKERY_W && y+j>=0 && y+j<Bakery.BAKERY_H){
                    Tile c = bakery.getMap()[x+i][y+j];
                    if(c.isAccessibleToRaccoon()&&c.isEmpty()){
                        neighbours.add(c);
                    }
                }
            }
        }
        if(neighbours.size() == 0) return t;
        return neighbours.get(Random.from(RandomGenerator.getDefault()).nextInt(neighbours.size())); 
    }
    
    /**
     * Function that makes the raccoon move away from the player
     * @return the tile to move to
     */
    public Tile moveAwayFromBaker(){
        int raccoon_x = this.position.getX();
        int raccoon_y = this.position.getY();
        //Find a tile around the raccoon that is away from the baker 
        //if none return curent position, raccon is blocked
        for(int i =-1 ; i<2;i++){
            for(int j=-1; j<2;j++){
                if(raccoon_y+j<Bakery.BAKERY_H && raccoon_y+j>=0 && raccoon_x+i>=0 && raccoon_x+i<Bakery.BAKERY_W-1 && this.bakery.getMap()[raccoon_x+i][raccoon_y+j].isAccessibleToRaccoon()){
                    return this.bakery.getMap()[raccoon_x+i][raccoon_y+j];
                }
            }
        }
        return this.position;
    }

    /** 
     * Function that returns the tile to which the raccoon will move
     */
    public Tile nextMove(){
        //search for the closest bread that is cooked
        Oven o = closestReadyBread(); 
        if (this.is_on_the_run()){
            return moveAwayFromBaker();
        }else if(o != null){ // a baked goods is cooked
            if(bakery.isCollectible(o, this.getPosition())){ //if bread is in front of it it ears it
                bakery.steal(o, this);
                return position;
            }else{ // not yet close enough so it moves towards it
                return moveTowardsBread(o);
            }
        } else { //if no bread is ready to be eaten, move randomly
            return randomNeighbour();
        }
    }
}