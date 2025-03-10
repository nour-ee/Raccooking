package Model;


public class Raccoon extends Entity {
    /****************
     *  ATTRIBUTES  *
     ****************/
    private Bakery map ;
    private int age = 0; // correspons to the life of the raccoon

    public int getAge() {
        return age;
    }

    /********************
    *    CONSTRUCTOR   *
    ********************/
    public Raccoon(Tile c, Bakery b){
        this.position = c;
        this.age = 0;
        this.map = b;
    }

    /********************
    *      METHODS      *
    ********************/


    /**
     * Method that changes the position of the raccoon
     * @param c the tile to move to
     */
    public void move(Tile c){
        this.position.RacoonLeft();
        this.position = c;
        this.position.RacoonArrived();
    }

    /**
     * Method that increments the age of the raccoon (i.e the number of move it has done)
     */
    public void increment(){
        this.age++;
    }   

    /** 
     * Function that returns the tile to which the raccoon will move
     */
    public Tile nextMove(){
        //search for the closest bread that is cooked
        Oven o = map.closestReadyBread(position); 
        if(o != null){
            return o;
        }else{ //if no bread is ready to be eaten, move randomly
            return map.randomNeighbour(position);
        }
    }


}