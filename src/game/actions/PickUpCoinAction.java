package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;
import game.items.Coin;

public class PickUpCoinAction extends PickUpItemAction {

    private Coin coin;

    public PickUpCoinAction (Coin coin) {
        super(coin);
        this.coin = coin;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        Wallet.getInstance().updateWallet(coin.getValue());
        return (actor + " gained $" + coin.getValue() + ". Total money: $" + Wallet.getInstance().getWalletValue());
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " picks up $" + coin.getValue() + " coin");
    }



}
