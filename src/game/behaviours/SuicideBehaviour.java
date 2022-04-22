package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SuicideAction;

import java.util.Random;

public class SuicideBehaviour extends Action implements Behaviour {

	private final Random random = new Random();

	/**
	 * Creates a 10% chance of suiciding the Actor (setting its health to 0)
	 * returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		int i;
		i = random.nextInt();

		if (i % 10 == 0){ //to get a 10% chance to auto kill
			return new SuicideAction();

		}

		return null;


	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
