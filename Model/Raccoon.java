package Model;


public class Raccoon extends Entity {
    /****************
     *  ATTRIBUTES  *
     ****************/
    private Bakery map ;
    private int age = 0; // correspons to the life of the raccoon
    private int nb_bread; // number of bread stolen by the raccoon

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Tile getPosition() {
        return position;
    }
    public int getBread() { return nb_bread;}
    /********************
    *    CONSTRUCTOR   *
    ********************/
    public Raccoon(Tile c, Bakery b){
        this.position = c;
        c.RacoonArrived();
        this.age = 0;
        this.map = b;
        this.nb_bread = 0;
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
        this.position.RacoonHasLeft();
        this.position = c;
        this.position.RacoonArrived();
    }

    /**
     * Method that increments the age of the raccoon (i.e the number of move it has done)
     */
    public void increment(){
        this.setAge(this.age+1);
    }   

    /**
     * Method that makes the raccoon eat the bread
     *@param o the oven from which the raccoon will eat the bread
     */
    public void eatBread(Oven o){
        o.removeBread();
        this.nb_bread++;
    }
    

    /** 
     * Function that returns the tile to which the raccoon will move
     */
    public Tile nextMove(){
        //search for the closest bread that is cooked
        Oven o = map.closestReadyBread(position); 
        if(o != null){
            //if a bread is cooked moves towards it
            if(o.getX() > position.getX()) return map.getMap()[position.getX()+1][position.getY()];
            if(o.getX() < position.getX()) return map.getMap()[position.getX()-1][position.getY()];
            if(o.getY()+1 > position.getY()) return map.getMap()[position.getX()][position.getY()+1];
            if(o.getY()+1 < position.getY()) return map.getMap()[position.getX()][position.getY()-1];
            else this.eatBread(o);return position;
        }else { //if no bread is ready to be eaten, move randomly
            return map.randomNeighbour(position);
        }
    }



}