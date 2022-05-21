package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * Can dig soft ground and maybe even find something
 */
public class Shovel extends Item implements Buyable{

    /**
     * Constructor.
     */
    public Shovel() {
        super("Shovel", 's', true);
        this.addCapability(Status.CAN_DIG);
    }

    @Override
    public void onBuy(Actor actor) {
        actor.addItemToInventory(this);
    }
}
