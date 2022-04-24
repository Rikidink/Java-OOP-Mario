package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;


abstract public class Tree extends Ground {

    protected Actor mario;
    protected int turnCount = 0;

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
    }
}
