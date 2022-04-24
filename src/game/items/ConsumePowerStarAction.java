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

    private int timeRemaining; //default value - just in case


    /**
     * Constructor.
     *
     * @param item the item to drop
     */

    public ConsumePowerStarAction(Item item) {
        this.item = item;
        this.timeRemaining = 22;
    }

    public ConsumePowerStarAction(Item item, int timeRemaining) {
        this.item = item;
        this.timeRemaining = timeRemaining;
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
        return actor + " consumes the Power Star - " + timeRemaining + " turns remaining";
    }

    /**
     * Get the amount of time left before the item is destroyed
     * @return
     */
    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }


}