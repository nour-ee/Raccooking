package Model;

/**
 * Classe Joueur : à voir si elle hérite d'une classe Entité ou pas
 * Le joueur connait sa position actuelle, il est posé sur une carte
 */
public class Player {
    private Tile position;
    private int argent;

    public Player(Tile c){
        this.position = c; this.argent = 0;
    }

    public void deplacer(Tile c){
        if(c.isTraversable()){
            this.position = c;
            System.out.println("Position du joueur : "+position.getX()+" "+position.getY());
            if(c.isPain()){
                c.setPain(false);
                argent += 10;
                System.out.println("Argent du joueur : "+argent);
            }
        }
    }

    public Tile getPosition() { return position; }
    public int getArgent() { return argent; }
}
