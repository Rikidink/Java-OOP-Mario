package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.DigAction;

public class SoftGround extends Ground {
    /**
     * Constructor.
     *
     */
    public SoftGround() {
        super('x');
    }

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
