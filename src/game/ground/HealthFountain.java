package game.ground;


import edu.monash.fit2099.engine.actions.Action;
import game.items.consumable.StorableFood;

/**
 * A fountain that has healing water
 */
public class HealthFountain extends Fountain{



    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H',"Health Fountain",10);
    }

    @Override
    public Action getAppropriateWaterAction() {
        return null;
    }

    @Override
    public StorableFood getAppropriateWaterInstance() {
        return null;
    }
}
