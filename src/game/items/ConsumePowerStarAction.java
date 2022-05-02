package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;

/**
 * Eat a power start
 *
 */
public class ConsumePowerStarAction extends Action {

    private final Item item;

    private int timeRemaining;

    /**
     * Constructor.
     *
     * @param powerStar the item to drop
     */
    public ConsumePowerStarAction(PowerStar powerStar, int timeRemaining) {
        this.item = powerStar;
        this.timeRemaining = timeRemaining;
    }

    /**
     * Perform the action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        map.locationOf(actor).removeItem(item);
        actor.heal(200); //heal by 200 hit points
        actor.addCapability(Status.HAS_EATEN_POWER_STAR); // adds a status
        actor.addCapability(Status.HAS_EATEN_POWER_STAR_THIS_TURN); // adds a status

        return menuDescription(actor);
    }

    /**
     * Creates a short description about eating the power start
     *
     * @param actor The actor performing the action.
     * @return the short description crested
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the Power Star - " + timeRemaining + " turns remaining";
    }

    /**
     * Get the amount of time left before the item is destroyed
     *
     */
    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
}
