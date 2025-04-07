package Model;

import Controller.Cooking;
import java.util.HashMap;


public class Croissant extends BakedGoods{

    public static HashMap<String, Integer> recipe;
    public Croissant () {
        super(15, 120, 200); //A croissant sells for 15 and needs 1 flour, 1 egg, and 2 butter
        cooking= new Cooking(this);

        recipe=new HashMap<String,Integer>();
        recipe.put("flour", 1);
        recipe.put("egg", 1);
        recipe.put("yeast", 0);
        recipe.put("butter", 2);

        cooking.start();
    }
}
