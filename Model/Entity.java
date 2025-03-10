package Model;

/**
 * Entity class : abstract class for all entities in the game
 * - player
 * - raccoon
 */
abstract class Entity {
    Tile position;
    public abstract void move(Tile t);
}
