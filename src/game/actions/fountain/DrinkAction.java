package game.actions.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.ModifiableIntrinsicWeaponActor;
import game.items.Bottle;
import game.items.fountain.Fountain;


/**
 * Action for drinking, currently from fountain or bottles
 */

public class DrinkAction extends Action {

    /**
     * what kinda thing are you drinking?
     */
    protected String type;

    /**
     * The Fountain where the water comes from
     */
    protected Fountain fountain;

    private boolean fromBottleFlag;

    /**
     * Constructor
     * @param type what thetype of fountain is
     * @param fountain fountain instance
     * @param fromBottleFlag if the drink action happens in a bottle
     */

    public DrinkAction(String type, Fountain fountain, boolean fromBottleFlag){
        this.type = type;
        this.fountain = fountain;
        this.fromBottleFlag = fromBottleFlag;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (fromBottleFlag) {
            Bottle.getInstance().consumeFromBottle();
            return (fountain.effects((ModifiableIntrinsicWeaponActor) actor));
        }
        else if (fountain.canDrinkFrom()) {
            fountain.reduceRemainingWater(1);
            return (fountain.effects((ModifiableIntrinsicWeaponActor) actor));
        }
        return "The fountain does not have enough water to drink from yet."; //this will never show up as the action wil
    }

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
