package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.Key;
import game.reset.Resettable;

public class Bowser extends Enemy implements Resettable {

    /**
     * Constructor.
     *
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        addItemToInventory(new Key());
        registerInstance();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return super.getIntrinsicWeapon(80, "punches");
    }

    @Override
    public void resetInstance(GameMap map) {
        //move back to original position, heal to max health, stop following.
    }
}
