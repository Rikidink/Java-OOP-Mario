package game.items.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.fountain.DrinkAction;
import game.actions.fountain.FillBottleAction;
import game.actors.ModifiableIntrinsicWeaponActor;

import java.util.ArrayList;
import java.util.List;

/**
 * A fountain full of magical replenishing water.
 */
public abstract class Fountain extends Item {

    /**
     * what type of water does the fountain hold?
     */
    protected String type;

    /**
     * how much water is left in the fountain?
     */
    protected int remainingWater = 10;

    /**
     * How much water the fountain can store?
     */
    protected int maxWater;

    /**
     * How much longer until the fountain refills itself?
     */
    protected int timeUntilRefill = 5;

    /**
     * Where is the fountain?
     */
    protected Location location;

    /**
     * Constructor
     *
     * @param displayChar what the fountain is displayed as
     * @param type the type of fountain
     * @param maxWater how much water the fountain can hold
     */
    public Fountain(char displayChar, String type, int maxWater) {
        super(type, displayChar, false);
        this.type = type;
        this.maxWater = maxWater;
        this.remainingWater = maxWater;
        this.addCapability(Status.IS_A_FOUNTAIN);
    }

    /**
     * Getter.
     *
     * Returns an unmodifiable copy of the actions list so that calling methods won't
     * be able to change what this Item can do without the Item checking.
     * @return an unmodifiable list of Actions
     * @see Item#getAllowableActions()
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();

        if (remainingWater > 0) {
            if (location.getActor().hasCapability(Status.CAN_FILL_BOTTLE)) {
                    actions.add(new FillBottleAction( getDrinkAction(true), this));
            }
        }
        if (remainingWater >= 5) {
            if (location.getActor().hasCapability(Status.CAN_DRINK)) {
                actions.add(getDrinkAction(false));
            }
        }
        return actions;
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param location The location of the ground on which we lie.
     * @see Item#tick(Location currentLocation)
     */
    @Override
    public void tick(Location location) {
        this.location = location;

        //does it refill or not?
        if (remainingWater == 0){ // a requirement is to only refill if water is at 0
            if (timeUntilRefill == 0){
                timeUntilRefill = 5;
                remainingWater = maxWater;
            }
            else {
                timeUntilRefill --;
                System.out.println( type + " at (" + location.x() + "," + location.y() + ") is empty. It will refill soon..."); // only print out if fountain is empty
            }
        }
    }

    /**
     * reduces the amount of water left
     *
     * @param reductionAmount how much to reduce the water by
     */
    public void reduceRemainingWater(int reductionAmount){
        remainingWater -= reductionAmount;

        if (remainingWater < 0) {
            remainingWater = 0;
        }
    }

    /**
     * If the fountain has enough water to refill a bottle
     *
     * @return if the fountain has enough water to fill a bottle
     */
    public boolean canFillBottle(){
        return remainingWater >= 1;
    }

    /**
     * If there is enough water to drink from the fountain
     *
     * @return if the fountain has enough water to drink from
     */
    public boolean canDrinkFrom() {
        return remainingWater >=5;
    }


    /**
     * Info about remaining water
     *
     * @return a String describing how much of the total water si currently in the fountain
     */
    public String getWaterInfo(){
        return "(" + remainingWater + "/" + maxWater + ")";
    }

    /**
     * applies the effects of the Fountain to an actor
     *
     * @param actor the actor to apply the effects of the fountain to
     * @return  a String describing the effects of the fountain
     */
    public abstract String effects(ModifiableIntrinsicWeaponActor actor);

    /**
     * action for getting a DrinkAction from a Fountain
     *
     * @param fromBottleFlag a flag for whether a DrinkAction is being taken from a Fountain or a Bottle
     * @return  a DrinkAction
     */
    protected abstract DrinkAction getDrinkAction(Boolean fromBottleFlag);
}
