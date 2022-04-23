package game.trees;

import edu.monash.fit2099.engine.positions.Ground;
import java.util.Random;

abstract public class Tree extends Ground {

    protected int turnCount = 0;

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
    }
}
