package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A Wrench item. This has the ability to be able ot kill dormant Koopas
 */
public class Wrench extends WeaponItem {

    public Wrench(Actor actor) {
        super("Wrench", 'W', 50, "wallops", 80);
        this.togglePortability();
    }
}
