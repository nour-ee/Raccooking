package Model;

import Controller.Cooking;

import java.util.HashMap;

public class Bread extends BakedGoods{

    public static final HashMap<String, Integer> RECIPE=Bread.init();
    public Bread () {
        super(5, 120, 200); //A bread sells for 5 and needs 1 flour, 1 yeast0);
        state=State.COOKING;

        cooking= new Cooking(this);
        cooking.start();
    }

    public static boolean canBake(HashMap<String, Integer> ingredients) {
        // Check if the ingredients contain the required amounts
        return ingredients.get("flour") >= 1 && ingredients.get("yeast") >= 1;
    }


    public static HashMap<String, Integer> getRecipe() {
        return RECIPE;
    }

    public static HashMap<String, Integer> init(){
        HashMap<String, Integer> recipe = new HashMap<>();
        recipe.put("flour", 1);
        recipe.put("egg", 0);
        recipe.put("yeast", 1);
        recipe.put("butter", 0);
        return recipe;
    }
}
