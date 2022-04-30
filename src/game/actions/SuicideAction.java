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
	 * Constructor
	 */
	public SuicideAction() {
	}

	/**
	 * Removes the actor from the game
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return the description for the actor being removed
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);
		return menuDescription(actor);
	}

	/**
	 * A description of actor being removed from the game
	 * @param actor The actor performing the action.
	 * @return the description
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " dies of natural causes";
	}
}
