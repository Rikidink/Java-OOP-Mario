package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Wallet;
import game.reset.Resettable;
import game.reset.ResetAction;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	int remainingInvincibility = -10;

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
		System.out.println("HP: " + printHp());
		System.out.println("Money: $" + Wallet.getInstance().getWalletValue());
		managePowerStar();

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
			if (status == Status.HAS_EATEN_POWER_STAR || status == Status.TALL) {
				this.removeCapability(status);
			}
		}
	}

	@Override
	public void registerInstance() {
		Resettable.super.registerInstance();
	}

	/**
	 *Manages effects of the PowerStar on the Player once it has been consumed
	 */
	private void managePowerStar() {
		if (this.hasCapability(Status.HAS_EATEN_POWER_STAR_THIS_TURN)){
			this.removeCapability(Status.HAS_EATEN_POWER_STAR_THIS_TURN);
			remainingInvincibility = 10;
		}

		if(this.hasCapability(Status.HAS_EATEN_POWER_STAR)){
			if (remainingInvincibility >= 1) {
				remainingInvincibility -= 1;
				System.out.println("Mario is INVINCIBLE!");
			} else {
				this.removeCapability(Status.HAS_EATEN_POWER_STAR);
			}
		}
	}
}
