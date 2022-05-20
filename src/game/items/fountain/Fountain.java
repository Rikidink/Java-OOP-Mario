package game.items.fountain;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.ModifiableIntrinsicWeaponActor;


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
     * Where is the founted? usefull is you need to know if the actor at the location has a bottle in its inventory
     */
    protected Location location;

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
        this.addCapability(Status.IS_A_FOUNTAIN);
    }

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
     */
    public void reduceRemainingWater(int reductionAmount){
        remainingWater -= reductionAmount;

        if (remainingWater < 0) {
            remainingWater = 0;
        }
    }

    /**
     * If the fountain has enough water to refill items
     */

    public boolean canFillBottle(){
        return remainingWater >= 1;
    }

    public boolean canDrinkFrom() {
        return remainingWater >=5;
    }


    /**
     * Info about remaining water
     */
    public String getWaterInfo(){
        return "(" + remainingWater + "/" + maxWater + ")";
    }

    public abstract String effects(ModifiableIntrinsicWeaponActor actor);


}

