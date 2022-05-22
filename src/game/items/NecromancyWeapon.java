package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

/**
 * A weapon capable of undead magic when held
 */
public class NecromancyWeapon extends WeaponItem {

    /**
     * How likely the weapon is to strike a hit, changes with the amount of kills the player gets
     */
    private int hitRate = 30;

    /**
     * how much damage the weapon does
     */
    private int damage = 10;

    /**
     * If it is the first time in an actors inventory, used to check for KILLER status
     */
    private boolean firstTime = true;

    /**
     * Constructor
     *
     */
    public NecromancyWeapon() {
        super("Necromancy Weapon", 'N', 10, "strikes", 30);
        this.addCapability(Status.CAN_REANIMATE);
    }

    /**
     * Returns the chance to hit the target in integer. Use it altogether with nextInt() method.
     *
     * @return the hitRate of the weapon
     * @see WeaponItem#chanceToHit()
     */
    @Override
    public int chanceToHit() {
        return hitRate;
    }

    /**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     * @see WeaponItem#damage()
     */
    @Override
    public int damage() {
        return damage;
    }

    /**
     * increase the chance of the weapon striking successfully
     *
     * @param amount % amount to increase by
     */
    public void increaseChanceToHit(int amount){
        hitRate += amount;

        if (hitRate > 100){
            hitRate = 100;
        }
    }

    /**
     * increase the damage of the weapon
     *
     * @param amount amount to increase the damage by
     */
    public void increaseDamage(int amount){
        damage += amount;
    }

    /**
     * tick item in actors inventory
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (firstTime) {
            firstTime = false;
        } else if (actor.hasCapability(Status.KILLER)){
            increaseChanceToHit(5);
            increaseDamage(5);
        }
        actor.removeCapability(Status.KILLER);
    }

    /**
     * tick actor on ground
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        firstTime = true;
    }
}


