package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.ground.fountain.Fountain;
import game.items.Bottle;

/**
 * To allow actions to add items to a bottle, assumaes actors can only hold one bottle at a time
 */
public abstract class CanHoldBottleActor extends Actor {


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

    /**
     * The Fountain where the water comes from
     */
    private Fountain fountain;

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

        if (bottle == null){
            this.removeCapability(Status.HAS_A_BOTTLE);
        } else {
            this.addCapability(Status.HAS_A_BOTTLE);
        }

    }

    public void setRefillType(String type){
        this.type = type;

    }

    public void setFountain(Fountain fountain){
        this.fountain = fountain;

    }
}
