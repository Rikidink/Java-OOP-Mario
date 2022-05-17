package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Status;
import game.Wallet;
import game.reset.Resettable;
import game.actions.ResetAction;

/**
 * Class representing the Player.
 */
public class Player extends CanHoldBottleActor implements Resettable {


	private final Menu menu = new Menu();

	/**lop
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
		this.addCapability(Status.LAVA_WALK);
		registerInstance();
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @see Actor#playTurn(ActionList actions, Action lastAction, GameMap map, Display display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		alsoDoThisWhenTicked();

		if (this.hasCapability(Status.INVINCIBLE)){
			System.out.println("Mario is INVINCIBLE!");
		}
		System.out.println("HP: " + printHp());
		System.out.println("Money: $" + Wallet.getInstance().getWalletValue());

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if (this.hasCapability(Status.CAN_RESET)) {
			actions.add(new ResetAction());
		}

		if (this.hasCapability(Status.NEEDS_TO_REFILL_BOTTLE)){
			refillTheBottle();
		}

		if (getBottle().getCurrentDrinkAction() != null && this.hasCapability(Status.HAS_A_BOTTLE)){ //add the action to drink from the bottle
			actions.add(getBottle().getCurrentDrinkAction());
		}
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 *
	 * @see Actor#getDisplayChar()
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Resets abilities, attributes, and/or items.
	 *
	 * @see Resettable#resetInstance(GameMap map)
	 */
	@Override
	public void resetInstance(GameMap map) {
		heal(getMaxHp());
		for (Enum<?> status : capabilitiesList()) {
			if (status == Status.INVINCIBLE || status == Status.TALL) {
				this.removeCapability(status);
			}
		}
	}


}
