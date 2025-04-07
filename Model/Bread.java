package Model;

import Controller.Cooking;

public class Bread extends BakedGoods{
    public Bread () {
        super(5, new int[]{1, 0, 1, 0}, 120, 200); //A bread sells for 5 and needs 1 flour, 1 yeast
        cooking= new Cooking(this);
        cooking.start();
    }
}
