package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An Action for when a mob suicides
 * 
 * Use this to implement waiting or similar actions in game clients.
 */
public class SuicideAction extends Action {

	/**
	 * Perform the Action
	 *
	 * @see Action#execute(Actor actor, GameMap map)
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);
		return menuDescription(actor);
	}

	/**
	 * Returns a descriptive String
	 *
	 * @see Action#execute(Actor actor, GameMap map)
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " dies of natural causes";
	}
}
