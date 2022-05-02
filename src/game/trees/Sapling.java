package game.trees;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Dirt;
import game.items.Coin;
import game.reset.Resettable;

/**
 * Sapling class; child class of tree
 *
 */
public class Sapling extends Tree {

    /**
     * Constructor for the sapling
     *
     * @param x x position of the sapling
     * @param y y position of the sapling
     */
    public Sapling(int x, int y){
        super('t', x, y, 0.8, 20, "Sapling");
    }

    /**
     * Function repeats every turn:
     * After 10 turns the sapling is replaced by a mature
     * Has 10 percent chance to spawn a coin at the sapling's location
     *
     * @param location The location of the Ground (i.e: sapling)
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        // after 10 turns the sapling is replaced with a sapling
        if(turnCount == 10){
            location.setGround(new Mature(location.x(), location.y()));
        }
        // 10 percent chance to spawn a coin on the sapling
        else {
            if(Math.random() <= 0.10){
                location.addItem(new Coin(20,location.x(), location.y()));
            }
        }
    }

    /**
     * Resets abilities, attributes, and/or items.
     *
     * @see Resettable#resetInstance(GameMap map)
     */
    @Override
    public void resetInstance(GameMap map) {
        if (Math.random() <= 0.5) {
            map.at(x, y).setGround(new Dirt());
        }
    }

}
