package game.trees;

import edu.monash.fit2099.engine.positions.Ground;

abstract public class Tree extends Ground {

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
    }

    public abstract void spawnObject();
}
