package game.items.consumable;

import edu.monash.fit2099.engine.items.Item;

/**
 * An item in the game world, not yet implimented
 *
 */
public class SuperMushroom extends Item {

	/***
	 * Constructor
	 *
	 */
	public SuperMushroom() {
		super("Super Mushroom", '^', true);
		super.addAction(new ConsumeSuperMushroomAction(this));
	}
}
