package game.actors.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.behaviours.GoToFountainBehaviour;
import game.reset.Resettable;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.items.consumable.SuperMushroom;

import java.util.Arrays;
import java.util.List;

/**
 * A big turtle guy.
 */
public abstract class Koopa extends Enemy implements Resettable {

	/**
	 * Constructor.
	 */
	public Koopa(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.CAN_BE_DORMANT); // adds a status
		this.addItemToInventory(new SuperMushroom()); //So it drops a supermushroom when it dies
		this.behaviours.put(7, new GoToFountainBehaviour());


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
		actions.add(super.allowableActions(otherActor, direction, map));
		if (!(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER))) {
			behaviours.put(8, new AttackBehaviour(otherActor));
		}

		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(this.hasCapability(Status.IS_DORMANT)) || otherActor.getWeapon().toString().equals("Wrench")) {
			actions.add(new AttackAction(this,direction)); //todo: add check for if dormant - remove from alloiwable acitons
		}

		if (this.hasCapability(Status.FOLLOWING)) {
			behaviours.put(8, new AttackBehaviour(otherActor));
		}
		return actions;
	}

	/**
	 * Figure out what to do next.
	 *
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		alsoDoThisWhenTicked();

		if (this.hasCapability(Status.IS_DORMANT)) { //if dormant...
			return new DoNothingAction();
		}
		else { //Koopa will only do an action if it is not dormant
			return super.playTurn(actions,lastAction,map,display);
			}
		}



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
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */

	public IntrinsicWeapon getIntrinsicWeapon() {
		return super.getIntrinsicWeapon(30, "punches");
	}

	@Override
	public char getDisplayChar(){
		if (this.hasCapability(Status.IS_DORMANT)) {
			return 'D';
		} else {
			return super.getDisplayChar();
		}
	}
}

