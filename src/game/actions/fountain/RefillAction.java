package game.actions.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountain.Fountain;
import game.items.Bottle;

/**
 * For refilling bottles at a fountain. Assumes the ground it is at is a fountain.
 */
public class RefillAction extends Action {

    /**
     * what kinda thing are you refilling with?
     */
    private String type;

    /**
     * Some information about the capacity of the container
     */
    private String waterInfo;

    /**
     * The bottle where the water is refilled to
     */
    private Bottle bottle;

    /**
     * The Fountain where the water comes from
     */
    private Fountain fountain;

    public RefillAction(String type, Fountain fountain){
        this.type = type;
        this.waterInfo = waterInfo;
        this.fountain = fountain;
        this.bottle = bottle;

    }
    @Override
    public String execute(Actor actor, GameMap map) {
        fountain.reduceRemainingWater();
        return actor +  " adds a portion of of " + type + " water to his bottle";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from " + type + " " + fountain.getWaterInfo();
    }
}
