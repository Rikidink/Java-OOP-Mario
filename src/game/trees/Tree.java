package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;


abstract public class Tree extends Ground implements Resettable {

    protected Actor mario;
    protected int turnCount = 0;
    protected int x;
    protected int y;

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar, int x, int y) {
        super(displayChar);
        this.x = x;
        this.y = y;
        registerInstance();
    }
}
