package Model;

import Controller.Cooking;
import java.util.HashMap;


public class Croissant extends BakedGoods{

    public static final HashMap<String, Integer> RECIPE=Croissant.init();
    public Croissant () {
        super(15, 120, 200); //A croissant sells for 15 and needs 1 flour, 1 egg, and 2 butter
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
