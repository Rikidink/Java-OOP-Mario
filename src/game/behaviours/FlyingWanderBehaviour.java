package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * Wander behaviour for things that can fly
 */
public class FlyingWanderBehaviour implements Behaviour{
    private final Random random = new Random();

    /**
     * gets the action the actor does (attack or not)
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return null or some info about the attack
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
                actions.add(exit.getDestination().getMoveAction(actor, exit.getName(), exit.getHotKey()));
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }
    }
}
