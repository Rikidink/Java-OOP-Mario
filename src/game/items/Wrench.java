package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Status;

/**
 * A Wrench item. This has the ability to be able ot kill dormant Koopas
 */
public class Wrench extends WeaponItem {

    public Wrench() {
        super("Wrench", 'W', 50, "wallops", 80);
    }
}
