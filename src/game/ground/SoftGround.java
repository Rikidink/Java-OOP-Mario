package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.DigAction;

/**
 * A type of ground which you can dig at
 */
public class SoftGround extends Ground {
    /**
     * Constructor.
     */
    public SoftGround() {
        super('x');
    }

    /**
     * Method that adds the jump action to higher ground objects
     *
     * @param actor the Actor acting; i.e: the player
     * @param location the current Location of the higher ground
     * @param direction the direction of the Ground from the Actor
     * @return the list of actions for the SoftGround
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.containsAnActor()) {
            if (actor.hasCapability(Status.CAN_DIG)) {
                actions.add(new DigAction());
            }
        }
        return actions;
    }
}
