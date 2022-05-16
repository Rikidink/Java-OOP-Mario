package game.actors;

import game.Status;
import game.actions.fountain.DrinkAction;
import game.actions.fountain.DrinkHealthWaterAction;
import game.actions.fountain.DrinkPowerWaterAction;
import game.items.Bottle;

/**
 * To allow actions to add items to a bottle, assumes actors can only hold one bottle at a time
 */
public abstract class CanHoldBottleActor extends ModifiableIntrinsicWeaponActor {


    /**
     * what kinda thing are you refilling with?
     */
    protected String type;

    /**
     * Some information about the capacity of the container
     */
    protected String waterInfo;

    /**
     * The bottle where the water is refilled to
     */
    protected Bottle bottle;


    CanHoldBottleActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

    }

    /**
     * get the bottle the actor has
     * @return the bottle
     */
    public Bottle getBottle() {
        return bottle;
    }

    /**
     * The the bottle to something specific, update statuses accordingly
     * @param bottle the bottle to set to
     */
    public void setBottle(Bottle bottle) {
        this.bottle = bottle;

//        if (bottle == null){
//            this.removeCapability(Status.HAS_A_BOTTLE);
//        } else {
//            this.addCapability(Status.HAS_A_BOTTLE);
//        }

    }

    public void setRefillType(String type){
        this.type = type;

    }
    /**
     * Actually refills the bottle (continues on from FakRefillAction)
     */
    protected void refillTheBottle(){
        this.removeCapability(Status.NEEDS_TO_REFILL_BOTTLE);
        bottle.addConsumableToBottle(getApproprotateWater());


    }

    protected DrinkAction getApproprotateWater(){
        if (this.hasCapability(Status.REFILL_TYPE_HEALTH)){
            this.removeCapability(Status.REFILL_TYPE_HEALTH);
            setRefillType("Health Fountain");
            return new DrinkHealthWaterAction(type, null, false);
        } else if (this.hasCapability(Status.REFILL_TYPE_POWER)){
            this.removeCapability(Status.REFILL_TYPE_POWER);
            setRefillType("Power Fountain");
            return new DrinkPowerWaterAction(type, null, false);



        }
        return null;
    }
}
