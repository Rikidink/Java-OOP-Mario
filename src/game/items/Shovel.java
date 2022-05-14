package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;

public class Shovel extends Item {

    /***
     * Constructor.
     * @param actor
     */
    public Shovel(Actor actor) {
        super("Shovel", 's', true);
        actor.addCapability(Status.CAN_DIG);
    }
}
