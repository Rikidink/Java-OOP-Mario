package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * A key which can end the game. ie the key to ending your misery
 */
public class Key extends Item {

    /**
     * Constructor.
     *
     */
    public Key() {
        super("Key", 'k', true);
        this.addCapability(Status.CAN_END_GAME);
    }
}
