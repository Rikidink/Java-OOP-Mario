package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.actions.PickUpCoinAction;

public class Coin extends Item implements Resettable {

    private Integer value;
    private int x;
    private int y;

    public Coin (Integer value, int x, int y) {
        super("Coin", '$', false);
        this.value = value;
        this.x = x;
        this.y = y;
        registerInstance();
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }

    @Override
    public void resetInstance(GameMap map) {
        map.at(x, y).removeItem(this);
    }

    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
