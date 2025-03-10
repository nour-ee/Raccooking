package Model;

/**
 * Classe Joueur : à voir si elle hérite d'une classe Entité ou pas
 * Le joueur connait sa position actuelle, il est posé sur une carte
 */
public class Player extends Entity {
    private int money; // current money of the player
    //TO DO : array with ingredients inventory
    private int[] ressources; //0 flour, 1 egg, 2 yeast, 3 butter
    private int soldBread; // number of breads sold

    public Player(Tile c){
        this.position = c; this.money = 0;
    }

    public void move(Tile c){
        if(c.isTraversable()){
            this.position = c;
            System.out.println("Position du joueur : "+position.getX()+" "+position.getY());
            if(c.isPain()){
                c.setPain(false);
                money += 10;
                System.out.println("Argent du joueur : "+ money);
            }
        }
    }

    public Tile getPosition() { return position; }
    public int getMoney() { return money; }
}
