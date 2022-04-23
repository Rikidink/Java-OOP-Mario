package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.actions.ResetAction;

/**
 * Class representing the Player.
 */
public class Player extends Actor  {

	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.CAN_RESET);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//check for mushroom capabilities
		if (this.hasCapability(Status.HAS_EATEN_SUPER_MUSHROOM)){ //if the player has eaten a Mushroom....
			//do all of the mushroom stuff
			this.setDisplayChar('M'); //the display character evolves to the uppercase letter (e.g., from m to M).
			this.addCapability(Status.NO_RECENT_DAMAGE); // to keep track of the no damage requirement

			//TODO: it can jump freely with a 100% success rate and no fall damage.
		}

		if(this.hasCapability(Status.HAS_EATEN_POWER_STAR)){
			System.out.println("Mario is INVINCIBLE!");
		}



		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if (this.hasCapability(Status.CAN_RESET)) {
			actions.add(new ResetAction());
		}
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Do some damage to the current Actor.
	 *
	 * If the Actor's hitpoints go down to zero, it will be knocked out.
	 * Special cases are also added for certain statuses
	 *
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {

		if (this.hasCapability(Status.HAS_EATEN_POWER_STAR)){
			points = 0;

		} else if (this.hasCapability(Status.NO_RECENT_DAMAGE)) { //when hit after taking a mushroom the character returns to normal
			this.removeCapability(Status.NO_RECENT_DAMAGE);
			this.setDisplayChar('m');

		}
		super.hurt(points);
	}

}
