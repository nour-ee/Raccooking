package Model;

import java.util.HashMap;

/**
 * Baker : our player class !
 */
public class Baker extends Entity {

    /****************
     *  ATTRIBUTES  *
     ****************/
    private int money; // current money of the player
    private HashMap<String, Integer> ressources; //Stores the ressources of the baker
                              // in the following indexes:
                              // 0 flour, 1 egg, 2 yeast, 3 butter
    private int soldBread; // number of breads sold
    private Bakery bakery; //the bakery 

    /****************
     *    GETTERS   *
     ****************/
    public Tile getPosition() { return position; }
    public int getMoney() { return money; }
    public int getSoldBread() { return soldBread; }
    public Tile getTile() { return position; }
    public HashMap<String, Integer> getRessources() { return ressources; }
    public Bakery getBakery() { return bakery; }

    /********************
     *    CONSTRUCTOR   *
     ********************/
    public Baker(Tile c, Bakery b){
        this.bakery = b;
        this.position = c;
        c.BakerArrived();
        this.money = 50;
        //this.ressources = new int[4];
        //for(int i = 0; i<4; i++) ressources[i] = 10;
        this.ressources = new HashMap<>();
        ressources.put("flour", 5); ressources.put("egg", 5);
        ressources.put("yeast", 5); ressources.put("butter", 5);
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

        if(c.isAccessibleToBaker()){
            //make the tiles next to old baker pos not have a nextToBaker
            for(Tile t : this.bakery.neighbours(this.position)){
                t.setNextToBaker(false);
            }
            this.position.BakerHasLeft();
            this.position = c;
            this.position.BakerArrived();
            //make the tiles next to new baker pos have a nextToBaker
            for(Tile t : this.bakery.neighbours(c)){
                t.setNextToBaker(true);
            }
            //makes all raccoons in proximity run away
            for(Raccoon r : bakery.raccoonsNextTo(c)){
                r.set_on_the_run(true);
            }
            System.out.println("Position du joueur : "+position.getX()+" "+position.getY());
        }
    }

     /**
     * Method that increments breads sold
     */
    public void sellBread(int price){
        soldBread++;
        money+= price;
    }

    /**
     * Method to buy ressources
     * @param s string representing the ressource to buy
     *          "flour", "egg", "yeast", "butter"
     */
    public void buy(String s){
        if( money >= 2 ) {
            /*
            switch (s) {
                case "flour" : ressources[0]++; money=money-2; break;
                case "egg" : ressources[1]++; money=money-2; break;
                case "yeast" : ressources[2]++; money=money-2; break;
                case "butter" : ressources[3]++; money=money-2; break;
            }*/
            ressources.put(s, ressources.get(s)+1); money-=2;
            System.out.println("Ressource "+s+" : "+ressources.get(s));
        }
    }

    public boolean canBake(){
        //return ressources[0] > 0 && ressources[1] > 0 && ressources[2] > 0 && ressources[3] > 0;
        for (int i : ressources.values()) {
            if(i<=0) return false;
        } return true;
    }

    public void spendRessources(){
        //ressources[0]--; ressources[1]--; ressources[2]--; ressources[3]--;
        for (String s : ressources.keySet()) {
            ressources.put(s, ressources.get(s)-1);
            System.out.println("Ressource "+s+" : "+ressources.get(s));
        }
    }


}
