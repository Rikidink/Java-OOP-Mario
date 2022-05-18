package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.fountain.DrinkAction;
import game.reset.Resettable;
import game.actions.AttackAction;
import game.behaviours.*;

import java.util.Arrays;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy implements Resettable {

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20, Arrays.asList(7), Arrays.asList( new GoToFountainBehaviour())); //changed health to 20
		this.behaviours.put(1, new SuicideBehaviour()); // the order here is important, first to last possible
		this.behaviours.put(10, new WanderBehaviour());
		registerInstance();
	}

	/**
	 * Returns a new collection of the Actions that the otherActor can do to the current Actor.
	 *
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();



		if (!(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER))) {
			behaviours.put(8, new AttackBehaviour(otherActor));
		}

		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}

		if (this.hasCapability(Status.FOLLOWING)) {
			behaviours.put(9, new FollowBehaviour(otherActor));
//			behaviours.put(8, new AttackBehaviour(otherActor));
		}
		return actions;
	}

	/**
	 * Figure out what to do next.
	 *
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */


	/**
	 * Resets abilities, attributes, and/or items.
	 *
	 * @see Resettable#resetInstance(GameMap map)
	 */
	@Override
	public void resetInstance(GameMap map) {
		map.removeActor(this);
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return super.getIntrinsicWeapon(10, "kicks");
	}
}
