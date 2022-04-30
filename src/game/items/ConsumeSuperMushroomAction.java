package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;

public class ConsumeSuperMushroomAction extends Action {

    /**
     * Current item
     */
    private final Item item;


    /**
     * Constructor.
     *
     * @param item the item to drop
     */

    public ConsumeSuperMushroomAction(Item item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        map.locationOf(actor).removeItem(item);
        actor.increaseMaxHp(50);  //increase max hp by 50
        actor.addCapability(Status.TALL); // adds a status

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the Super Mushroom";
    }

}
