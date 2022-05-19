package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.actions.SuicideAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;

import java.util.HashMap;
import java.util.Map;

/**
 * A zombie. Helps kill/convert enemies for the player. is not an enemy
 */
public class Zonbi extends Actor implements Resettable {

    protected final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * How long the Zonbie has left to live
     */
    private int turnsLeft = 20;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Zonbi(String name, char displayChar, int hitPoints) {
        super("Zonbi", 'z', 20);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        behaviours.put(10, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //Zonbie will only live for at most 10 turns
        turnsLeft --;
        if (turnsLeft == 0){
            return new SuicideAction();

        }
        return null;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();



        if (!(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER))) {
            behaviours.put(8, new AttackBehaviour(otherActor));
        }

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }

        if (this.hasCapability(Status.FOLLOWING)) {
            behaviours.put(9, new FollowBehaviour(otherActor));
            behaviours.put(8, new AttackBehaviour(otherActor));
        }
        return actions;
    }


    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "bites");
    }

    @Override
    public void resetInstance(GameMap map) {

    }

}
