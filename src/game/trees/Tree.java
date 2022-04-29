package game.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.HigherGround;
import game.Resettable;
import game.actions.JumpAction;


abstract public class Tree extends HigherGround implements Resettable {

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

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new JumpAction(location, actor, direction));
        return actions;
    }


}
