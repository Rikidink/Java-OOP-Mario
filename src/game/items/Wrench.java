package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A Wrench item. This has the ability to be able ot kill dormant Koopas
 */
public class Wrench extends WeaponItem implements Buyable{
    /**
     * Constructor
     */
    public Wrench() {
        super("Wrench", 'W', 50, "wallops", 80);
    }

    @Override
    public void onBuy(Actor actor) {
        actor.addItemToInventory(this);
    }
}
