package Model;

/**
 * Entity class : abstract class for all entities in the game
 * - player
 * - raccoon
 */
abstract class Entity {

    /****************
     *  ATTRIBUTES  *
     ****************/
    Tile position; //current position of the entity


    /****************
     *    METHODS   *
     ****************/

    /**
     * Abstract method to move our entity to the given tile
     * @param T the tile to move to
     */
    public abstract void move(Tile T);
}
