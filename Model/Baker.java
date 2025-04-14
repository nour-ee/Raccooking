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
        this.ressources = new HashMap<>();
        ressources.put("flour", 5); ressources.put("egg", 5);
        ressources.put("yeast", 5); ressources.put("butter", 5);
        this.soldBread = 0;
    }

    /****************
     *    METHODS   *
     ****************/

    /**
     * Move the player to a certain tile
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
        }
    }

     /**
     * Counts the number of breads sold
      * @param price the price of the bread
     */
    public void sellBread(int price){
        soldBread++;
        money+= price;
    }

    /**
     * Buy a selected ressource
     * @param s string representing the ressource to buy
     *          "flour", "egg", "yeast", "butter"
     */
    public void buy(String s, int quantity){
        boolean bought = false;

        switch (s) {
            case "flour" :
                if(money >= 2){ bought = true; ressources.put(s, ressources.get(s)+quantity); money-=2*quantity; } break;
            case "egg" :
                if(money >= 4){ bought = true; ressources.put(s, ressources.get(s)+quantity); money-=3*quantity; } break;
            case "yeast" :
                if(money >= 5){ bought = true; ressources.put(s, ressources.get(s)+quantity); money-=5*quantity; } break;
            case "butter" :
                if(money >= 3){ bought = true; ressources.put(s, ressources.get(s)+quantity); money-=4*quantity; } break;
        }

        //Small debug message
        if( bought ) {
            System.out.println("Ressource "+s+" achetée : "+ressources.get(s));
        }
    }

    /**
     * Method to check if the baker can bake a certain recipe
     * @param recipe the recipe to check
     * @return true if the baker can bake the recipe, false otherwise
     */
    public boolean canBake(HashMap<String, Integer> recipe) {
        // Check if the ingredients contain the required amounts
        for (String ingredient : recipe.keySet()) {
            if (ressources.get(ingredient) < recipe.get(ingredient)) {
                return false;
            }
        }
        return true;
    }

/**
     * Method to spend the ressources
     * @param recipe the recipe to spend
     */
    public void spendRessources(HashMap<String, Integer> recipe){
        for (String ingredient : recipe.keySet()) {
            ressources.put(ingredient, ressources.get(ingredient) - recipe.get(ingredient));
            System.out.println("Ressource "+ingredient+" : "+ressources.get(ingredient));
        }
    }
}
