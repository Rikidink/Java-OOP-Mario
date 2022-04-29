package game.trees;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.items.Coin;

/**
 * Sapling class; child class of tree
 */
public class Sapling extends Tree {

    /**
     * The success rate if a player jumps to sapling
     */
    private final double successRate = 0.8;

    /**
     * The fall damage if a player's jump is unsuccessful
     */
    private final int fallDamage = 20;

    /**
     * The name of the sapling as a string
     */
    private final String highGroundName = "Sapling";

    /**
     * Constructor for the sapling
     * @param x x position of the sapling
     * @param y y position of the sapling
     */
    public Sapling(int x, int y){
        super('t', x, y);
    }

    /**
     * Function repeats every turn:
     * After 10 turns the sapling is replaced by a mature
     * Has 10 percent chance to spawn a coin at the sapling's location
     * @param location The location of the Ground (i.e: sapling)
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        // after 10 turns the sapling is replaced with a sapling
        if(turnCount == 10){
            location.setGround(new Mature(mario, location.x(), location.y()));
        }
        // 10 percent chance to spawn a coin on the sapling
        else {
            if(Math.random() <= 0.10){
                location.addItem(new Coin(20,location.x(), location.y()));
            }
        }

    }

    @Override
    public void resetInstance(GameMap map) {
        if (Math.random() <= 0.5) {
            map.at(x, y).setGround(new Dirt());
        }
    }

    @Override
    public void registerInstance() {
        super.registerInstance();
    }

    /**
     * Method to return the success rate
     * @return the success rate
     */
    @Override
    public double getSuccessRate() {
        return successRate;
    }

    /**
     * Method to return the fall damage
     * @return the fall damage
     */
    @Override
    public int getFallDamage() {
        return fallDamage;
    }

    /**
     * Method to return the name of the sapling (Sapling)
     * @return Sprout as a string
     */
    @Override
    public String getHighGroundName() {
        return highGroundName;
    }
}
