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
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();

        if (remainingWater > 0) {
            if (location.getActor().hasCapability(Status.CAN_FILL_BOTTLE)) {
                if (remainingWater > 0) {
                    actions.add(new FillBottleAction(new DrinkAction("Healing water", this, true), this));
                }
            }

            if (location.getActor().hasCapability(Status.CAN_DRINK)) {
                actions.add(new DrinkAction("Healing water", this, false));
            }
        }
        return actions;
    }

    @Override
    public String effects(ModifiableIntrinsicWeaponActor actor) {
        actor.heal(50);
        return "The healthy water heals " + actor + " by 50 points";
    }
}
