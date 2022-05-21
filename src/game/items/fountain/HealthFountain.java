package game.items.fountain;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.actions.fountain.DrinkAction;
import game.actions.fountain.FillBottleAction;
import game.actors.ModifiableIntrinsicWeaponActor;

import java.util.ArrayList;
import java.util.List;

/**
 * A fountain that has healing water
 */
public class HealthFountain extends Fountain {

    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H', "Health Fountain", 10);
    }


    @Override
    public String effects(ModifiableIntrinsicWeaponActor actor) {
        actor.heal(50);
        return "The healthy water heals " + actor + " by 50 points";
    }

    @Override
    protected DrinkAction getDrinkAction(Boolean fromBottleFlag) {
        return new DrinkAction("Health Fountain", this, fromBottleFlag);
    }
}
