package game.items.fountain;


import edu.monash.fit2099.engine.actions.Action;
import game.actions.fountain.DrinkAction;
import game.actions.fountain.DrinkHealthWaterAction;

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
    public DrinkAction getAppropriateWaterAction(boolean directlyRelated) {
        return new DrinkHealthWaterAction(super.type, this, directlyRelated);

    }
}
