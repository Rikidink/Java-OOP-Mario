package game.actions.fountain;

/**
 * Doesn't actually refill, tells the actor that it needs to refill
 */

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.fountain.Fountain;

/**
 * For refilling bottles at a fountain. Assumes the ground it is at is a fountain.
 */
public class FakeRefillAction extends Action {

    /**
     * what kinda thing are you refilling with?
     */
    private String type;

    /**
     * The Fountain where the water comes from
     */
    private Fountain fountain;

    public FakeRefillAction(String type, Fountain fountain){
        this.type = type;
        this.fountain = fountain;

    }
    @Override
    public String execute(Actor actor, GameMap map) {
        fountain.reduceRemainingWater();
        actor.addCapability(Status.NEEDS_TO_REFILL_BOTTLE);

        //add the appropritate refill type enum to the actor
        if (type == "Health Fountain"){
            actor.addCapability(Status.REFILL_TYPE_HEALTH);
        }
        return actor +  " adds a portion of " + type + " water to his bottle";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from " + type + " " + fountain.getWaterInfo();
    }
}

