package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.GoToFountainAction;

/**
 * The behaviour for actors to go to a fountain by them self
 */
public class GoToFountainBehaviour implements Behaviour{


    @Override
    public Action getAction(Actor actor, GameMap map) {

        // there is a 10% chance each turn to go start going to the closest fountain, if there is a fountain in the world

        GoToFountainAction goToFountainAction = new GoToFountainAction();

        boolean isThereAFountainToGoTO = goToFountainAction.findClosestFountainCords(map,actor);

        if (Math.random() < 0.1 && isThereAFountainToGoTO){
            actor.addCapability(Status.WANTS_TO_GO_TO_A_FOUNTAIN);
        }

        // - go to the closest fountain regardless of the fountain type
        if (actor.hasCapability(Status.WANTS_TO_GO_TO_A_FOUNTAIN)){
            return goToFountainAction;
        }
        return null;
    }

}
