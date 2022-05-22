package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * The normal Koopa
 */
public class NormalKoopa extends Koopa {

    /**
     * Constructor
     */
    public NormalKoopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(10, new WanderBehaviour());
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
            behaviours.put(8, new FollowBehaviour(otherActor));
        }

        return actions;
    }
}
