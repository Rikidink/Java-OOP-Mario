package game.items.fountain;

import game.actions.fountain.DrinkAction;
import game.actors.ModifiableIntrinsicWeaponActor;

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

    /**
     * applies the effects of the Fountain to an actor
     *
     * @param actor the actor to apply the effects of the fountain to
     * @return  a String describing the effects of the fountain
     * @see Fountain#effects(ModifiableIntrinsicWeaponActor actor)
     */
    @Override
    public String effects(ModifiableIntrinsicWeaponActor actor) {
        actor.heal(50);
        return "The healthy water heals " + actor + " by 50 points";
    }

    /**
     * action for getting a DrinkAction from a Fountain
     *
     * @param fromBottleFlag a flag for whether a DrinkAction is being taken from a Fountain or a Bottle
     * @return  a DrinkAction
     * @see Fountain#getDrinkAction(Boolean fromBottleFlag)
     */
    @Override
    protected DrinkAction getDrinkAction(Boolean fromBottleFlag) {
        return new DrinkAction("Health Fountain", this, fromBottleFlag);
    }
}
