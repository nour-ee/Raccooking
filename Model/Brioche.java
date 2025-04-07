package Model;

import Controller.Cooking;

public class Brioche extends BakedGoods {
    public Brioche() {
        super(20, new int[]{1, 1, 1, 1}, 120, 200); // A brioche sells for 20 and needs 1 flour, 1 egg, 1 yeast, and 1 butter
        cooking = new Cooking(this);
        cooking.start();
    }
}
