package game.actions.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.ModifiableIntrinsicWeaponActor;
import game.items.Bottle;
import game.items.fountain.Fountain;


/**
 * An Action for drinking from a fountain or a drink stored in the bottle
 */

public class DrinkAction extends Action {

    /**
     * The type of fountain that the DrinkAction has come from
     */
    protected String type;

    /**
     * The Fountain where the DrinkAction has come from
     */
    protected Fountain fountain;

    /**
     * A flag for whether the action is to drink directly from the fountain, or to drink from a bottle
     */
    private boolean fromBottleFlag;

    /**
     * Constructor
     *
     * @param type              The type of fountain that the DrinkAction has come from
     * @param fountain          The Fountain where the DrinkAction has come from
     * @param fromBottleFlag    A flag for whether the action is to drink directly from the fountain, or to drink from a bottle
     */
    public DrinkAction(String type, Fountain fountain, boolean fromBottleFlag){
        this.type = type;
        this.fountain = fountain;
        this.fromBottleFlag = fromBottleFlag;
    }

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (fromBottleFlag) {
            Bottle.getInstance().consumeFromBottle();
            return (fountain.effects((ModifiableIntrinsicWeaponActor) actor));
        }
        else if (fountain.canDrinkFrom()) {
            fountain.reduceRemainingWater(5);
            return (fountain.effects((ModifiableIntrinsicWeaponActor) actor));
        }
        return "The fountain does not have enough water to drink from yet.";
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        //for bottles
        if (fromBottleFlag) {
            StringBuilder bottleOutput = new StringBuilder();

            for (DrinkAction action : Bottle.getInstance().getBottleContents()) {
                bottleOutput.append(type + " Water" + ", ");
            }
            bottleOutput.deleteCharAt(bottleOutput.length() -1);
            bottleOutput.deleteCharAt(bottleOutput.length() -1);
            return actor + " drinks from Bottle [" + bottleOutput + "]";
        }
        //for sipping
        return actor + " drinks the water from the " + type + " " +  fountain.getWaterInfo();
    }
}
