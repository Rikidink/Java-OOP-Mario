package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.fountain.DrinkAction;
import game.actors.ModifiableIntrinsicWeaponActor;
import game.behaviours.Behaviour;
import game.behaviours.GoToFountainBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A generic enemy
 */
public abstract class Enemy extends ModifiableIntrinsicWeaponActor {

    /**
     * What behaviours does the enemy have?
     */
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Constructor
     *
     * @param name name of the enemy
     * @param displayChar what character the enemy is displayed as
     * @param hitPoints how much health the enemy has
     * @param baseDamage how much damage the enemy does by default
     * @param verb verb used when enemy attacks
     */
    public Enemy(String name, char displayChar, int hitPoints, int baseDamage, String verb){
        super(name, displayChar, hitPoints, baseDamage, verb);
        this.addCapability(Status.CANNOT_ENTER_FLOOR);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.addCapability(Status.CAN_DRINK);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(super.allowableActions(otherActor, direction, map));
        return actions;
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for (Action action: actions){
            // definitely drink from a fountain
            if (action instanceof DrinkAction){
                return action;
            }
        }

        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
