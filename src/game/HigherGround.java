package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;

import java.util.ArrayList;

abstract public class HigherGround extends Ground {

    protected double successRate;
    protected int fallDamage;
    protected String highGroundName;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HigherGround(char displayChar) {
        super(displayChar);
    }

    public abstract double getSuccessRate();

    public abstract int getFallDamage();

    public abstract String getHighGroundName();

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if(!location.containsAnActor()){
            actions.add(new JumpAction(location, actor, direction, (HigherGround) location.getGround()));
        }
         return actions;

    }

}
