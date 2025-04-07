package Model;

import Controller.Cooking;

public class Croissant extends BakedGoods{
    public Croissant () {
        super(15, new int[]{1, 1, 0, 2}, 120, 200); //A croissant sells for 15 and needs 1 flour, 1 egg, and 2 butter
        cooking= new Cooking(this);
        cooking.start();
    }
}
