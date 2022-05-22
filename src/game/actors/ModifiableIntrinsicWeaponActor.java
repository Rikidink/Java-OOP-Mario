package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A type of Actor that can modify its intrinsic weapon
 */

public abstract class ModifiableIntrinsicWeaponActor extends Actor {

    /**
     * The current base damage of the Actor's intrinsic weapon
     */
    private int baseDamage;

    /**
     * The output verb for the Actor's intrinsic weapon
     */
    String verb;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param baseDamage  The current base damage of the Actor's intrinsic weapon
     * @param verb        The current output verb for the Actor's intrinsic weapon
     */
    public ModifiableIntrinsicWeaponActor(String name, char displayChar, int hitPoints, int baseDamage, String verb) {
        super(name, displayChar, hitPoints);
        this.baseDamage = baseDamage;
        this.verb = verb;
    }

    /**
     * Create a new intrinsic weapon for the actor (with modified damage)
     *
     * @param modifier  the amount of damage to add to the intrinsic weapon damage
     */
    public void modifyIntrinsicWeaponDamage(int modifier) {
        baseDamage += modifier;
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     * @see Actor#getIntrinsicWeapon()
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(baseDamage, verb);
    }
}
