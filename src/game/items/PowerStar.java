package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

/**
 * The power star item, greatest of all items. He who consumes this shall gain power unmatched
 *
 */
public class PowerStar extends Item {

	private int timeRemaining = 10; // time remaining until item is  destroyed
	private ConsumePowerStarAction consumePowerStarAction = new ConsumePowerStarAction(this, timeRemaining);

	/***
	 * Constructor
	 *
	 */
	public PowerStar() {
		super("Power Star", '*', true);
		super.addAction(consumePowerStarAction); //this is only added to the actionlist once so I must save it to later edit it
	}

	/**
	 * Inform a carried Item of the passage of time.
	 * This method is called once per turn, if the Item is being carried.
	 * If 10 turns have passed since create the item is removed from the game
	 *
	 * @param currentLocation The location of the actor carrying this Item.
	 * @param actor The actor carrying this Item.
	 */
	@Override
	public void tick(Location currentLocation, Actor actor) {
		timeRemaining --;
		if (timeRemaining == 0) {
			actor.removeItemFromInventory(this);
		} else {
			consumePowerStarAction.setTimeRemaining(timeRemaining);
		}
	}

	/**
	 * Inform an Item on the ground of the passage of time.
	 * This method is called once per turn, if the item rests upon the ground.
	 * If 10 turns have passed since create the item is removed from the game
	 *
	 * @param currentLocation The location of the ground on which we lie.
	 */
	@Override
	public void tick(Location currentLocation) {
		timeRemaining--;
		if (timeRemaining == 0) {
			currentLocation.removeItem(this);
		} else {
			consumePowerStarAction.setTimeRemaining(timeRemaining);
		}
	}
}
