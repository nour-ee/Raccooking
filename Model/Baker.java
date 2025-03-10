package Model;

/**
 * Baker : our player class !
 */
public class Baker extends Entity {

    /****************
     *  ATTRIBUTES  *
     ****************/
    private int money; // current money of the player
    private int[] ressources; //Stores the ressources of the baker
                              // in the following indexes:
                              // 0 flour, 1 egg, 2 yeast, 3 butter
    private int soldBread; // number of breads sold

    /****************
     *    GETTERS   *
     ****************/
    public Tile getPosition() { return position; }
    public int getMoney() { return money; }

    /********************
     *    CONSTRUCTOR   *
     ********************/
    public Baker(Tile c){
        this.position = c; this.money = 0;
        this.ressources = new int[4];
        this.soldBread = 0;
    }

    /****************
     *    METHODS   *
     ****************/

    /**
     * Method to move the player
     * @param c the tile to move to
     */
    public void move(Tile c){
        if(c.isAccessible()){
            this.position = c;
            System.out.println("Position du joueur : "+position.getX()+" "+position.getY());
    
        }
    }

     /*
     * Method that increments breads sold
     */
    public void sellBread(){
        soldBread++;
    }

    /**
     * Method to buy ressources
     * @param s string representing the ressource to buy
     *          "flour", "egg", "yeast", "butter"
     */
    public void buy(String s){
        if( money >= 2 ) {
            switch (s) {
                case "flour" : ressources[0]++; money=money-2; break;
                case "egg" : ressources[1]++; money=money-2; break;
                case "yeast" : ressources[2]++; money=money-2; break;
                case "butter" : ressources[3]++; money=money-2; break;
            }
        }
    }


}
