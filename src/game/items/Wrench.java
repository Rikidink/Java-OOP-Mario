package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A Wrench item. This has the ability to be able ot kill dormant Koopas
 */
public class Wrench extends WeaponItem {

    public Wrench() {
            super("Wrench", 'W', 50, "wrenches", 80); //the verb is the only way to directly tell what type of weapon item it is so this has to be specific to a wrench
        }



}
