package game.actions.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.fountain.Fountain;

/**
 * Action for filling bottles from a fountain
 */
public class FillBottleAction extends Action {

    /**
     * The DrinkAction to be stored in the bottle
     */
    private final DrinkAction drinkAction;

    /**
     * The fountain that the DrinkAction came from
     */
    private final Fountain fountain;

    /**
     * Constructor
     *
     * @param drinkAction   The DrinkAction to be stored in the bottle
     * @param fountain      The fountain that the DrinkAction came from
     */
    public FillBottleAction(DrinkAction drinkAction, Fountain fountain) {
        this.drinkAction = drinkAction;
        this.fountain = fountain;
    }

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (fountain.canFillBottle()){
            Bottle.getInstance().addConsumableToBottle(drinkAction);
            fountain.reduceRemainingWater(1);
            return "The bottle is filled with " + drinkAction.type;
        }
        else {
            return "The fountain does not have enough water to refill your bottle yet.";
        }
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills bottle from " + fountain;
    }
}
