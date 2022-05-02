package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import game.ground.HigherGround;
import game.reset.Resettable;


/**
 * An abstract tree class that is the parent of the three tree child classes,
 * implements the HigherGround abstract class since trees can be jumped on
 *
 */
abstract public class Tree extends HigherGround implements Resettable {

    /**
     * Integer to count turns for turn-based functionality
     *
     */
    protected int turnCount = 0;

    /**
     * X position of the tree
     *
     */
    protected int x;

    /**
     * Y position of the tree
     *
     */
    protected int y;


    /**
     *
     * @param displayChar the letter display of a tree
     * @param x x position of a tree
     * @param y y position of a tree
     */
    public Tree(char displayChar, int x, int y, double successRate, int fallDamage, String highGroundName) {
        super(displayChar, successRate, fallDamage, highGroundName);
        this.x = x;
        this.y = y;
        registerInstance();
    }

    /**
     * Method that determines if an actor can enter a wall
     *
     * @param actor the Actor to check
     * @return false, can't enter a wall
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
