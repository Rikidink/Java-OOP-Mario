package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.GoToFountainBehaviour;
import game.reset.Resettable;

import java.util.Arrays;

public class PiranhaPlant extends Enemy implements Resettable {

    /**
     * Constructor.
     *
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150, Arrays.asList(), Arrays.asList());
        registerInstance();
    }



    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return super.getIntrinsicWeapon(90, "chomps");
    }

    @Override
    public void resetInstance(GameMap map) {
        this.increaseMaxHp(50);
        this.heal(getMaxHp());
    }

}
