package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	/**
	 * Constructor
	 *
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Implements impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @see Ground#canActorEnter(Actor actor)
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.CANNOT_ENTER_FLOOR);
	}
}
