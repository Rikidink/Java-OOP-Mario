package game;

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

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " dies of natural causes";
	}
	

}
