package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.behaviours.*;

/**
 * A variant of the dreaded Koopa that can fly to follow the actor. It can fly now? It can fly now!!!
 */
public class FlyingKoopa extends Koopa {

    /**
     * Constructor
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        this.behaviours.put(10, new FlyingWanderBehaviour());
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(super.allowableActions(otherActor, direction, map));

        if (this.hasCapability(Status.FOLLOWING)) {
            behaviours.put(8, new FlyingFollowBehaviour(otherActor));
        }
        return actions;
    }
}
