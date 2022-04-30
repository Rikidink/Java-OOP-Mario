package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.Resettable;
import game.actions.PickUpCoinAction;

/**
 * A special type of Item that is a Coin
 */
public class Coin extends Item implements Resettable {

    private Integer value;
    private int x;
    private int y;

    /**
     * Constructor
     *
     * @param value The value of the Coin (dollar amount)
     * @param x     The x value of the location of the Coin
     * @param y     The y value of the location of the Coin
     */
    public Coin (Integer value, int x, int y) {
        super("Coin", '$', false);
        this.value = value;
        this.x = x;
        this.y = y;
        registerInstance();
    }

    /**
     * Getter for the value of a given Coin
     *
     * @return the value of the Coin (dollar amount)
     */
    public Integer getValue() {
        return value;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }

    /**
     * Resets abilities, attributes, and/or items.
     *
     * @see Resettable#resetInstance(GameMap map)
     */
    @Override
    public void resetInstance(GameMap map) {
        map.at(x, y).removeItem(this);
    }
}
