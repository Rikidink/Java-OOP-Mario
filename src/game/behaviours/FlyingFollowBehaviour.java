package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Behaviour for things that fly and follow
 */
public class FlyingFollowBehaviour implements Behaviour{

    private final Actor target;

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public FlyingFollowBehaviour(Actor subject) {
        this.target = subject;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor)) {
            return null;
        }

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        Location destination;

        for (Exit exit : here.getExits()) {
            destination = exit.getDestination();
                int newDistance = distance(destination, there);

                if (newDistance < currentDistance || currentDistance == 1) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        return null;
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
