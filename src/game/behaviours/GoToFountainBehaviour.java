package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.GoToFountainAction;

public class GoToFountainBehaviour implements Behaviour{




    @Override
    public Action getAction(Actor actor, GameMap map) {

        // there is a 10% chance each turn to go start going to the closest fountain
        if (Math.random() < 0.1){
            actor.addCapability(Status.WANTS_TO_GO_TO_A_FOUNTAIN);
        }

        // - go to the closest fountain regardless of the fountain type
        if (actor.hasCapability(Status.WANTS_TO_GO_TO_A_FOUNTAIN)){
            return new GoToFountainAction(actor, map);
        }



        return null;
    }

}
