package game.items.fountain;

import game.actions.fountain.DrinkAction;
import game.actions.fountain.DrinkHealthWaterAction;
import game.actions.fountain.DrinkPowerWaterAction;

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
    public DrinkAction getAppropriateWaterAction(boolean directlyRelated) {
        return new DrinkPowerWaterAction(super.type, this, directlyRelated);
    }
}


