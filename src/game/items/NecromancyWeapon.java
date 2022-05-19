package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A weapon capale of undead magic when held
 */
public class NecromancyWeapon extends WeaponItem {

    /**
     * How likely the weapon is to strike a hit, changes with the amount of kills the palayer gets
     */
    private int hitRate = 30;


    private int damage = 10;

    public NecromancyWeapon() {
        super("Necromancy Weapon", 'N', 10, "strikes", 30);
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

    }

    public void increaseDamage(int amount){
        damage += amount;

    }

}


