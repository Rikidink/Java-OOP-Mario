package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
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
     */
    public Zonbi() {
        super("Zonbi", 'z', 20);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        behaviours.put(10, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //Zonbie will only live for at most 10 turns
        if (turnsLeft == 0){
            return new SuicideAction();
        }

        for (Exit exit : map.locationOf(this).getExits()) {
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_PLAYER)) {
                behaviours.put(8, new AttackBehaviour(exit.getDestination().getActor()));
                behaviours.put(9, new FollowBehaviour(exit.getDestination().getActor()));
            }
        }

        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        turnsLeft --;
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            actions.add(new AttackAction(this,direction));
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
