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

	}

	/**
	 * Getter.
	 *
	 * Allows an actor -eg player - to potentially do more than just
	 * pick up or drop the mushroom
	 */
	public List<Action> getAllowableActions() {
		super.addAction(new ConsumeSuperMushroomAction(this));
		//get if to only work if off the ground - NOT NEEDED


		return super.getAllowableActions();
	}




}
