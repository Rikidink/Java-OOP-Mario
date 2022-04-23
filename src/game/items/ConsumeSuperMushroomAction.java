package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

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
        actor.removeItemFromInventory(this.item);
        actor.increaseMaxHp(50);  //increase max hp by 50

        //the display character evolves to the uppercase letter (e.g., from m to M).
        //to be done inside actor somehow, I add a capability I think

        //it can jump freely with a 100% success rate and no fall damage.
        //???
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the tasty Super Mushroom";
    }
}
