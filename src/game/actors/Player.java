package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Resettable;
import game.Status;
import game.actions.ResetAction;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

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
		registerInstance();
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//check for mushroom capabilities
		if (this.hasCapability(Status.HAS_EATEN_SUPER_MUSHROOM)){ //if the player has eaten a Mushroom....
			//do all of the mushroom stuff
			this.setDisplayChar('M'); //the display character evolves to the uppercase letter (e.g., from m to M).

			//TODO: it can jump freely with a 100% success rate and no fall damage.5

		} else {
			this.setDisplayChar('m'); //undo mushroom character change

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

	@Override
	public void resetInstance(GameMap map) {
		heal(getMaxHp());
		for (Enum<?> status : capabilitiesList()) {
			if (status == Status.HAS_EATEN_POWER_STAR || status == Status.HAS_EATEN_SUPER_MUSHROOM) {
				this.removeCapability(status);
			}
		}
	}

	@Override
	public void registerInstance() {
		Resettable.super.registerInstance();
	}
}
