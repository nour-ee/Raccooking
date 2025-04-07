package Model;

import Controller.Cooking;

import java.util.HashMap;

public class Brioche extends BakedGoods {

    public static HashMap<String, Integer> recipe;

    public Brioche() {
        super(20, 120, 200); // A brioche sells for 20 and needs 1 flour, 1 egg, 1 yeast, and 1 butter
        cooking = new Cooking(this);
        recipe = new HashMap<>();
        recipe.put("flour", 1);
        recipe.put("egg", 1);
        recipe.put("yeast", 1);
        recipe.put("butter", 1);
        cooking.start();
    }


}
