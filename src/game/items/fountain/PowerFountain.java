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
 * A fountain that has powerful water
 */

public class PowerFountain extends Fountain {
    /**
     * Constructor.

     */
    public PowerFountain() {
        super('A', "Power Fountain", 10);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();

        if (remainingWater > 0) {
            if (location.getActor().hasCapability(Status.CAN_FILL_BOTTLE)) {
                if (remainingWater > 0) {
                    actions.add(new FillBottleAction(new DrinkAction("Power water", this, true), this));
                }
            }

            if (location.getActor().hasCapability(Status.CAN_DRINK)) {
                actions.add(new DrinkAction("Power water", this, false));
            }
        }
        return actions;
    }

    @Override
    public String effects(ModifiableIntrinsicWeaponActor actor) {
        actor.modifyIntrinsicDamage(15);
        return (actor +" embraces the grind. The powerfull water increases " + actor + "'s base attack damage by 15 points");
    }
}


