package Model;

import java.util.HashMap;

import Model.Threads.Cooking;

public class Brioche extends BakedGoods {

    public static final HashMap<String, Integer> RECIPE=Brioche.init();

    public Brioche() {
        // A brioche sells for 20 euros
        // It cooks for 12 seconds
        // It burns after 24 seconds
        // It needs 1 flour, 1 egg, 1 yeast and 1 butter
        super(20, 12, 24); 
        state=State.COOKING;

        cooking =new Cooking(this);
        cooking.start();
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
