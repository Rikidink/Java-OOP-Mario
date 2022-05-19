package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

/**
 * A weapon capale of undead magic when held
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

    public NecromancyWeapon() {
        super("Necromancy Weapon", 'N', 10, "strikes", 30);
        this.addCapability(Status.CAN_REANIMATE);
    }

    @Override
    public int chanceToHit() {
        return hitRate;
    }

    @Override
    public int damage() {
        return damage;
    }


    public void increaseChanceToHit(int amount){
        hitRate += amount;

        if (hitRate > 100){
            hitRate = 100;
        }

    }

    public void increaseDamage(int amount){
        damage += amount;

    }

    /**
     * tick item in actors inventory
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (firstTime) {
            firstTime = false;
        } else {
            increaseChanceToHit(5);
            increaseDamage(5);


        }

        actor.removeCapability(Status.KILLER);
    }

    /**
     * tick actor on ground
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        firstTime = true;
    }
}


