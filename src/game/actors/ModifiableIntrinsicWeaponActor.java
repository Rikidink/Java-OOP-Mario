package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;

/**
 * modifies the intrinsic weapon of the actor class it inherits from
 */

public abstract class ModifiableIntrinsicWeaponActor extends Actor {

    /**
     * amount ot adjust the damage by
     */
    private int intrinsicDamageAdjuster;

    public ModifiableIntrinsicWeaponActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * gets an intrinsic weapon with the modifiers of this class
     * @param verb the verb printed
     * @param damage how much damage to do initially
     * @return an updates intrinsic weapon
     */
     protected IntrinsicWeapon getIntrinsicWeapon(int damage, String verb) {
        //int hitChance = oldIntrinsicWeapon.chanceToHit();

        return new IntrinsicWeapon(damage + intrinsicDamageAdjuster, verb);
    }


    /**
     * default intrinsic weapon stats
     * @return an updates intrinsic weapon
     */
    protected IntrinsicWeapon getIntrinsicWeapon() {
        //int hitChance = oldIntrinsicWeapon.chanceToHit();

        return new IntrinsicWeapon(5 + intrinsicDamageAdjuster, "punches");
    }


    /**
     * modify the intrinsic dmaage of a weapon
     * @param modifyBy the amount to modify by
     */
    public void modifyIntrinsicDamage(int modifyBy){
            intrinsicDamageAdjuster += modifyBy;
    }

    protected void alsoDoThisWhenTicked(){
        if (this.hasCapability(Status.NEEDS_TO_BOOST_POWER_15)){
            this.removeCapability(Status.NEEDS_TO_BOOST_POWER_15);
            modifyIntrinsicDamage(15);


        }
    }
}
