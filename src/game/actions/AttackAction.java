package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.actors.Zonbi;
import game.actors.enemies.Corpse;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target 	The Actor to attack
	 * @param direction The direction of incoming attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Perform the Action
	 *
	 * @see Action#execute(Actor actor, GameMap map)
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		target.addCapability(Status.FOLLOWING);
		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		String result;

		int damage = calculateDamage(actor,map, weapon);

		target.hurt(damage);

		if (!target.isConscious() && target.hasCapability(Status.CAN_BE_DORMANT)) {
			//if target in going to dormant state...
			target.addCapability(Status.IS_DORMANT);
			target.removeCapability(Status.CAN_BE_DORMANT);
			result = actor + " " + weapon.verb() + " " + target + ". " + target + " becomes dormant.";

		}
		else if (target.hasCapability(Status.IS_DORMANT) && !(weapon.toString().equals("Wrench"))){ //trying to attack a dormant actor with ineffective weapon
			result = actor + " " + weapon.verb() + " " + target + ". The dormant " + target + " snickers at the " + actor + " behind his very tough shell.";
		}
		else { // else actor can be attacked normally

			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			if (!target.isConscious()) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor or replace with Corpse

				if (target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
					Location spawnLocation = map.locationOf(target);
					map.removeActor(target);
					spawnLocation.addActor(new Corpse());
				}
				else {
					map.removeActor(target);
				}
				result += System.lineSeparator() + target + " is killed.";
				actor.addCapability(Status.KILLER);
			}
		}
		return result;
	}

	/**
	 * Returns a descriptive String
	 *
	 * @see Action#execute(Actor actor, GameMap map)
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}


	/**
	 * Calculates the damage to be done to the target, taking into account any powerups
	 *
	 * @param actor		The Actor doing the attack
	 * @param map		The Map this is occurring on
	 * @param weapon	The weapon the attack is being made with
	 * @return			The final damage to be done to the target
	 */
	private int calculateDamage(Actor actor, GameMap map, Weapon weapon) {
		int damage;

		if (actor.hasCapability(Status.INVINCIBLE)) { //power stars give one shot abilities
			damage = 9001;
		} else {
			damage = weapon.damage();
		}

		if (target.hasCapability(Status.INVINCIBLE)) { //power stars make all damage 0
			damage = 0;
		} else if (target.hasCapability(Status.TALL)) {
			target.removeCapability(Status.TALL);
		}
		return damage;
	}


}
