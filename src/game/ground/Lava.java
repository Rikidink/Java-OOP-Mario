package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * The floor is lava.. literally. A type of ground that is somehow less damaging than ground that is 'only' on fire
 */
public class Lava extends DamagingGround {

    /**
     * Constructor.
     */
    public Lava() {
        super('L', 15);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.LAVA_WALK);
    }

}