package Model;

import Controller.Cooking;
import java.util.HashMap;


public class Croissant extends BakedGoods{

    public static final HashMap<String, Integer> RECIPE=Croissant.init();
    public Croissant () {
        // A croissant sells for 15 euros
        // It cooks for 12 seconds
        // It burns after 30 seconds
        // It needs 1 flour, 1 egg, 0 yeast and 2 butter
        super(15, 12, 30); 
        state=State.COOKING;

        cooking= new Cooking(this);
        cooking.start();
    }

    public static HashMap<String, Integer> getRecipe() {
        return RECIPE;
    }

    public static HashMap<String, Integer> init(){
        HashMap<String, Integer> recipe = new HashMap<>();
        recipe.put("flour", 1);
        recipe.put("egg", 1);
        recipe.put("yeast", 0);
        recipe.put("butter", 2);
        return recipe;
    }
}
