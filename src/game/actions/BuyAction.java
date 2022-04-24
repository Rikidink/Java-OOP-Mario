package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;

public class BuyAction extends Action {

    private Actor buyer;
    private Item item;
    private Integer price;

    public BuyAction(Actor buyer, Item item, Integer price) {
        this.buyer = buyer;
        this.item = item;
        item.togglePortability();
        this.price = price;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (Wallet.getInstance().getWalletValue() >= price) {
            Wallet.getInstance().removeFromWallet(price);
            actor.addItemToInventory(item);
            return (actor + " buys " + item.toString());
        }
        else {
            return "You don't have enough coins!";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " buys " + item.toString() + " ($" + price + ")");
    }
}
