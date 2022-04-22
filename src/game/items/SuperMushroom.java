package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;


import java.util.List;

/**
 * An item in the game world, not yet implimented
 *
 */
public class SuperMushroom extends Item {

//	/***
//	 * Constructor.
//	 *
//	 * @param name the name of this Item
//	 * @param displayChar the character to use to represent this item if it is on the ground
//	 * @param portable true if and only if the Item can be picked up
//	 */
	public SuperMushroom() {
		super("Super Mushroom", '^', true);

	}

	/**
	 * Uses the item
	 * If this Item is not usable, returns null.
	 * @return null or the item
	 */
//	public UseItemAction UseItemAction(Actor actor) {
//		if(actor.getInventory()) {
//			return new UseItemAction(this);
//		}
//		return null;
//	}
//	public List<Action> getAllowableActions() {
//		List<Action> list = new UseItemAction();
//		return list;
//	}

}
