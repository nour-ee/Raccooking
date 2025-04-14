package Model;

import java.util.HashMap;

/**
 * Class to manage the croissant
 * It will manage the state of the croissant and the time it takes to cook it
 * It will also manage the price of the croissant and the recipe
 */
public class Croissant extends BakedGoods{
    /*************
     * CONSTANTS
     ************/
    public static final HashMap<String, Integer> RECIPE=Croissant.init();

    /*************
     * CONSTRUCTOR
     *************/
    public Croissant () {
        // A croissant sells for 28 euros
        // It cooks for 12 seconds
        // It burns after 30 seconds
        // It needs 1 flour, 1 egg, 0 yeast and 2 butter
        super(28, 12, 30);
    }

    /**
     * Method to get the recipe of the croissant
     * @return the recipe of the croissant
     */
    public static HashMap<String, Integer> getRecipe() {
        return RECIPE;
    }

    /**
     * Method to initialize the recipe of the croissant
     * @return the recipe of the croissant in a HashMap
     */
    public static HashMap<String, Integer> init(){
        HashMap<String, Integer> recipe = new HashMap<>();
        recipe.put("flour", 1);
        recipe.put("egg", 1);
        recipe.put("yeast", 0);
        recipe.put("butter", 2);
        return recipe;
    }
}
