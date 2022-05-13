package game.ground.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.fountain.DrinkHealthWaterAction;

import java.util.ArrayList;
import java.util.List;


/**
 * A fountain full of magical replenishing water. A sip takes away 5, a fill takes away 5.
 * ie each Health water class is worth 5 water in the fountain
 */
public abstract class Fountain extends Item {

    /**
     * what kinda thing are you refilling with?
     */
    protected String type;

    /**
     * how much water is left in the fountain
     */
    protected int remainingWater = 10;

    /**
     * How much water the fountain can store
     */
    protected int maxWater;

    /**
     * How much longer until the fountain refills itself?
     */
    protected int timeUntilRefill = 5;


    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     *
     */
    public Fountain(char displayChar, String type, int maxWater) {
        super(type, displayChar, false);
        this.type = type;
        this.maxWater = maxWater;
        this.remainingWater = maxWater;
    }


    @Override
    public List<Action> getAllowableActions() {
        System.out.println("chaingng allowble actions");
        //ActionList actions = new ActionList();

        List<Action> actions = new ArrayList<>();



        //I need a fill bottle action as well as a consume action?
        //if there is enough water left
        if (remainingWater > 0) {
            //allow actor to sip water
            actions.add(new DrinkHealthWaterAction(type,this, true));


            boolean canRefilled = false;
//
//            for (Item item : actor.getInventory()) { //if an actor has a refillable item in his inventory allow it to be filled
//                if (item.hasCapability(Status.CAN_BE_REFILLED)) {
//                    canRefilled = true;
//                }
//            }

            if (canRefilled == true) {
                //actions.add(new RefillAction());
            }


        }
        return actions;

    }

    /**
     * Get the appropriate water type. For sipping water
     * @return an instance of the corrosponding water action type
     */
    abstract public Action getAppropriateWaterAction();


    public void tick(Location location) {

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
     */
    public void reduceRemainingWater(){
        if (remainingWater > 4){
            remainingWater -= 5;
        }
        else{
            remainingWater = 0;
        }

    }

    /**
     * If the fountain has enough water to refill items
     */

    public boolean canRefillItem(){
        return remainingWater > 4;
    }


    /**
     * Info about remaining water
     */
    public String getWaterInfo(){
        return "(" + remainingWater + "/" + maxWater + ")";
    }


}

