package game.items.fountain;

import game.actions.fountain.DrinkAction;
import game.actors.ModifiableIntrinsicWeaponActor;

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

    /**
     * applies the effects of the Fountain to an actor
     *
     * @param actor the actor to apply the effects of the fountain to
     * @return  a String describing the effects of the fountain
     * @see Fountain#effects(ModifiableIntrinsicWeaponActor actor)
     */
    @Override
    public String effects(ModifiableIntrinsicWeaponActor actor) {
        actor.modifyIntrinsicWeaponDamage(15);
        return (actor +" embraces the grind. The powerful water increases " + actor + "'s base attack damage by 15 points");
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
        return new DrinkAction("Power Fountain", this, fromBottleFlag);
    }
}


