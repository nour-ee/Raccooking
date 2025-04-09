package Model;

import Controller.Cooking;

import java.util.HashMap;

public class Brioche extends BakedGoods {

    public static final HashMap<String, Integer> RECIPE=Brioche.init();

    public Brioche() {
        super(20, 120, 200); // A brioche sells for 20 and needs 1 flour, 1 egg, 1 yeast, and 1 butter
        state=State.COOKING;

        Cooking cooking =new Cooking(this);
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
