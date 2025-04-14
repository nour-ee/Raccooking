package Model;

import java.util.HashMap;
/**
 * Class to manage the brioche
 * It will manage the state of the brioche and the time it takes to cook it
 * It will also manage the price of the brioche and the recipe
 */
public class Brioche extends BakedGoods {

    /*************
     * CONSTANTS
     ************/
    public static final HashMap<String, Integer> RECIPE=Brioche.init();

    /*************
     * CONSTRUCTOR
     *************/
    public Brioche() {
        // A brioche sells for 30 euros
        // It cooks for 12 seconds
        // It burns after 24 seconds
        // It needs 1 flour, 1 egg, 1 yeast and 1 butter
        super(30, 12, 24);
    }

    /**
     * Method to get the recipe of the brioche
     * @return the recipe of the brioche
     */
    public static HashMap<String, Integer> getRecipe() {
        return RECIPE;
    }

    /**
     * Method to initialize the recipe of the brioche
     * @return the recipe of the brioche in a HashMap
     */
    private static HashMap<String, Integer> init() {
        HashMap<String, Integer> recipe = new HashMap<>();
        recipe.put("flour", 1);
        recipe.put("egg", 1);
        recipe.put("yeast", 1);
        recipe.put("butter", 1);
        return recipe;
    }
}
