package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.actions.PickUpCoinAction;

public class Coin extends Item {

    Integer value;

    public Coin (Integer value) {
        super("Coin", '$', false);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }
}
