package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;
import game.items.Coin;

/**
 * A variation on the PickUpItem class that specifies what happens when a Coin is picked up
 */
public class PickUpCoinAction extends PickUpItemAction {

    /**
     * The Coin that will be picked up by this Action
     */
    private final Coin coin;

    /**
     * Constructor
     *
     * @param coin The Coin that will be picked up by this Action
     */
    public PickUpCoinAction (Coin coin) {
        super(coin);
        this.coin = coin;
    }

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        Wallet.getInstance().addToWallet(coin.getValue());
        actor.removeItemFromInventory((coin));
        return (actor + " gained $" + coin.getValue() + ". Total money: $" + Wallet.getInstance().getWalletValue());
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " picks up $" + coin.getValue() + " coin");
    }
}
