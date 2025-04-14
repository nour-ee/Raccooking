package Model;

import java.util.HashMap;

public class Brioche extends BakedGoods {

    public static final HashMap<String, Integer> RECIPE=Brioche.init();

    public Brioche() {
        // A brioche sells for 30 euros
        // It cooks for 12 seconds
        // It burns after 24 seconds
        // It needs 1 flour, 1 egg, 1 yeast and 1 butter
        super(30, 12, 24);
    }

    public static HashMap<String, Integer> getRecipe() {
        return RECIPE;
    }

    private static HashMap<String, Integer> init() {
        HashMap<String, Integer> recipe = new HashMap<>();
        recipe.put("flour", 1);
        recipe.put("egg", 1);
        recipe.put("yeast", 1);
        recipe.put("butter", 1);
        return recipe;
    }

}
