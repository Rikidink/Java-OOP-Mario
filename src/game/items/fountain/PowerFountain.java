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



    @Override
    public String effects(ModifiableIntrinsicWeaponActor actor) {
        actor.modifyIntrinsicWeaponDamage(15);
        return (actor +" embraces the grind. The powerful water increases " + actor + "'s base attack damage by 15 points");
    }

    @Override
    protected DrinkAction getDrinkAction(Boolean fromBottleFlag) {
        return new DrinkAction("Power Fountain", this, fromBottleFlag);
    }
}


