package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;


import java.util.List;

/**
 * An item in the game world, not yet implimented
 *
 */
public class SuperMushroom extends Item {


	/***
	 * Constructor
	 */
	public SuperMushroom() {
		super("Super Mushroom", '^', true);

		super.addAction(new ConsumeSuperMushroomAction(this)); //add can be consumed as an allowable action
		//get if to only work if off the ground - NOT NEEDED

	}

}
