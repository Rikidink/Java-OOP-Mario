package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

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
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		target.addCapability(Status.FOLLOWING);
		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();

		if (actor.hasCapability(Status.HAS_EATEN_POWER_STAR)){ //power stars give one shpt abilities
			damage = 9001;
		}

		if (target.hasCapability(Status.HAS_EATEN_POWER_STAR)){ //power stars make all damage 0
			damage = 0;
		}

		if (damage != 0 && actor.hasCapability(Status.HAS_EATEN_SUPER_MUSHROOM)) { //getting git for non zero damage removes the super mushroom effect
			actor.removeCapability(Status.HAS_EATEN_SUPER_MUSHROOM);
		}
		target.hurt(damage);

		String result = ""; //placeholder so INTELLIJ lets me compile the code

		if (!target.isConscious() && target.hasCapability(Status.CAN_BE_DORMANT)) {
			//if target in going to dormant state...
			target.addCapability(Status.IS_DORMANT);
			target.removeCapability(Status.CAN_BE_DORMANT);
			result = actor + " " + weapon.verb() + " " + target + ". " + target + " becomes dormant.";

		} else if (target.hasCapability(Status.IS_DORMANT) && !(weapon.verb().equals("wrenches"))){ //trying to attack a dormant actor with ineffective weapon
			result = actor + " " + weapon.verb() + " " + target + ". The dormant " + target + " snickers at the " + actor + " behind his very tough shell.";

		} else { // else actor can be attacked normally

			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			if (!target.isConscious()) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";

			}
		}

		return result;
	}


	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
