package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * modifies the intrinsic weapon of the actor class it inherits from
 */

public abstract class ModifiableIntrinsicWeaponActor extends Actor {

    private IntrinsicWeapon currentIntrinsicWeapon;
    private int baseDamage;
    String verb;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public ModifiableIntrinsicWeaponActor(String name, char displayChar, int hitPoints, int baseDamage, String verb) {
        super(name, displayChar, hitPoints);
        this.baseDamage = baseDamage;
        this.verb = verb;
        currentIntrinsicWeapon = new IntrinsicWeapon(baseDamage, verb);
    }

    /**
     * Create a new intrinsic weapon for the actor (with modified damage)
     * @param modifier
     */
    public void createNewIntrinsicWeapon(int modifier) {
        baseDamage += modifier;
        currentIntrinsicWeapon = new IntrinsicWeapon((baseDamage), verb);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return currentIntrinsicWeapon;
    }
}
