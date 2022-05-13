package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.RefillAction;
import game.items.Bottle;
import game.items.consumable.ConsumeAction;
import game.items.consumable.PowerWater;
import game.items.consumable.StorableFood;

import java.sql.Ref;

/**
 * A fountain full of magical replenishing water. A sip takes away 5, a fill takes away 5.
 * ie each Health water class is worth 5 water in the fountain
 */
public abstract class Fountain extends Ground {

    /**
     * what kinda thing are you refilling with?
     */
    private String type;

    /**
     * how much water is left in the fountain
     */
    protected int remainingWater = 10;

    /**
     * How much water the fountain can store
     */
    private int maxWater;

    /**
     * How much water currently un the fountain
     */
    private int currentWater;

    /**
     * How much longer until the fountain refills itself?
     */
    private int timeUntilRefill = 5;


    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     *
     */
    public Fountain(char displayChar, String type, int maxWater) {
        super(displayChar);
        this.type = type;
        this.maxWater = maxWater;
        this.currentWater = maxWater;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList(new ConsumeAction(new PowerWater(), "drink the water from the fountain"));

        //I need a fill bottle action as well as a consume action?

        //if there is enough water left


        if (remainingWater > 0) {
            boolean canRefilled = false;

            for (Item item : actor.getInventory()) { //if an actor has a refillable item in his inventory allow it to be filled
                if (item.hasCapability(Status.CAN_BE_REFILLED)) {
                    canRefilled = true;
                }
            }

            if (canRefilled == true) {
                //actions.add(new RefillAction());
            }
            //allow actor to sip water
            actions.add(getAppropriateWaterAction());

        }
        return actions;

    }

    /**
     * Get the appropriate water type. For sipping water
     * @return an instance of the corrosponding water action type
     */
    abstract public Action getAppropriateWaterAction();


    /**
     * Get an appropriate water type instance. For refilling bottles
     * @return an instance of the corrosponding water type
     */
    abstract public StorableFood getAppropriateWaterInstance();

    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        //does it refill or not?
        if (remainingWater == 0){
            if (timeUntilRefill == 0){
                timeUntilRefill = 5;
                remainingWater = maxWater;
            }
            else {
                timeUntilRefill --;
            }
        }
    }

    /**
     * reduces the amount of water left
     */
    public void reduceRemainingWater(){
        if (remainingWater > 4){
            remainingWater -= 5;
        }

    }

    /**
     * If the fountain has enough water to refill items
     */

    public boolean canRefillItem(){
        return remainingWater > 4;
    }
}

