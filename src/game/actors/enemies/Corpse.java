package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.ReanimateAction;

/**
 * What becomes of an Enemy when it dies
 */
public class Corpse extends Enemy{

    private int timeRemaining;

    /**
     * Constructor
     */
    public Corpse() {
        super("Corpse", '@', 9001, 0, "doesn't attack");
        timeRemaining = 5;
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

        if (otherActor.hasCapability(Status.CAN_REANIMATE)) {
            actions.add(new ReanimateAction(this));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (timeRemaining == 0) {
            map.removeActor(this);
        }
        timeRemaining--;

        return super.playTurn(actions, lastAction, map, display);
    }
}
