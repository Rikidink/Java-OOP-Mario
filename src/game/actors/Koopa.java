package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;
import game.items.Wrench;

import java.util.HashMap;
import java.util.Map;

/**
 * A little fungus guy.
 */
public class Koopa extends Actor implements Resettable {
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour


	/**
	 * Constructor.
	 */
	public Koopa(Actor player) {
		super("Koopa", 'K', 100); //100 hp
		this.addCapability(Status.CAN_BE_DORMANT); // adds a status
		this.addCapability(Status.CANNOT_ENTER_FLOOR);
		this.addCapability(Status.HOSTILE_TO_PLAYER);
		this.behaviours.put(10, new WanderBehaviour());
		this.addItemToInventory(new SuperMushroom()); //So it drops a supermushroom when it dies
		registerInstance();
	}

	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
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
		}		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(this.hasCapability(Status.IS_DORMANT)) || otherActor.getWeapon().toString().equals("Wrench")) {
			actions.add(new AttackAction(this,direction));
		}
		if (this.hasCapability(Status.FOLLOWING)) {
			behaviours.put(9, new FollowBehaviour(otherActor));
			behaviours.put(8, new AttackBehaviour(otherActor));
		}
		return actions;
	}

	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (this.hasCapability(Status.IS_DORMANT)) { //if dormant...
			this.setDisplayChar('D');
		} else { //Koopa will only do an action if it is not dormant
			for (Behaviour Behaviour : behaviours.values()) {
				Action action = Behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
		}
		return new DoNothingAction();
	}

	@Override
	public void resetInstance(GameMap map) {
		map.removeActor(this);
	}

	@Override
	public void registerInstance() {
		Resettable.super.registerInstance();
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(30, "punches");
	} //weapon hitrates are 50% by default

}

