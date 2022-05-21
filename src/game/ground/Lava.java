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


    /**
     * Functions states that only actors with the LAVA_WALK status can walk onto lava
     * @param actor the Actor to check
     * @return true if the actor has LAVA_WALK, false if they don't
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.LAVA_WALK);
    }

}