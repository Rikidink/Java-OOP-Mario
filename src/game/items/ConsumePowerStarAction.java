package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public class ConsumePowerStarAction extends Action {

    /**
     * Current item
     */
    private final Item item;


    /**
     * Constructor.
     *
     * @param item the item to drop
     */

    public ConsumePowerStarAction(Item item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        map.locationOf(actor).removeItem(item);
        actor.heal(200); //heal by 200 hit points
        actor.addCapability(Status.HAS_EATEN_POWER_STAR); // adds a status

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the Power Star";
    }
}
