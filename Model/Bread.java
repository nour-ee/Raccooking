package Model;

import java.util.HashMap;
/**
 * Class to manage the bread
 * It will manage the state of the bread and the time it takes to cook it
 * It will also manage the price of the bread and the recipe
 */
public class Bread extends BakedGoods{

    public static final HashMap<String, Integer> RECIPE=Bread.init();
    /*************
     * CONSTRUCTOR
     ***********/
    public Bread () {
        // A breads sells for 20 euros
        // It cooks for 15 seconds
        // It burns after 30 seconds
        // It needs 1 flour, 0 egg, 1 yeast and 0 butter
        super(20, 15, 30); 
        state=State.COOKING;
    }

    /**
     * Method to get the recipe of the bread
     * @return the recipe of the bread
     */

    public static HashMap<String, Integer> getRecipe() {
        return RECIPE;
    }

    /**
     * Method to initialize the recipe of the bread
     * @return the recipe of the bread in a HashMap
     */
    public static HashMap<String, Integer> init(){
        HashMap<String, Integer> recipe = new HashMap<>();
        recipe.put("flour", 1);
        recipe.put("egg", 0);
        recipe.put("yeast", 1);
        recipe.put("butter", 0);
        return recipe;
    }
}
