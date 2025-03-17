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
    public int getSoldBread() { return soldBread; }

    /********************
     *    CONSTRUCTOR   *
     ********************/
    public Baker(Tile c){
        this.position = c; this.money = 50;
        this.ressources = new int[4];
        for(int i = 0; i<4; i++) ressources[i] = 10; //rn we have a default number of ressources to test around stuff : might change later
        this.soldBread = 0;
    }

    /****************
     *    METHODS   *
     ****************/

    /**
     * Method to move the player
     * @param c the tile to move to
     */
    @Override
    public void move(Tile c){

        if(c.isAccessible()){
            this.position.BakerHasLeft();
            this.position = c;
            this.position.BakerArrived();
            System.out.println("Position du joueur : "+position.getX()+" "+position.getY());
    
        }
    }

     /**
     * Method that increments the number of breads sold
      * and the money of the player
     */
    public void sellBread(){
        soldBread++;
        money+=Bread.PRICE;
    }

    /**
     * Method to buy ingredients in order to bake our bread
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

    /**
     * Checks if the player has enough ingredients to bake bread
     * @return b : true if the player has enough ingredients, false otherwise
     */
    public boolean canBake(){
        return ressources[0] > 0 && ressources[1] > 0 && ressources[2] > 0 && ressources[3] > 0;
    }

    /**
     * Decrement the ressources of the player when he bakes a bread
     */
    public void spendRessources(){
        ressources[0]--; ressources[1]--; ressources[2]--; ressources[3]--;
    }


}
