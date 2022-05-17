package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;
import game.items.Buyable;

/**
 * An Action type for buying an Item from another Actor
 */
public class BuyAction extends Action {

    private final Buyable item;
    private final Integer price;

    /**
     * Constructor
     *
     * @param item  The Item that the BuyAction allows the Actor to buy
     * @param price The price of the Item that relates to the BuyAction
     */
    public BuyAction(Buyable item, Integer price) {
        this.item = item;
        this.price = price;
    }

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (Wallet.getInstance().getWalletValue() >= price) {
            Wallet.getInstance().removeFromWallet(price);
            item.onBuy(actor);
            return (actor + " buys " + item);
        }
        else {
            return "You don't have enough coins!";
        }
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " buys " + item.toString() + " ($" + price + ")");
    }
}
