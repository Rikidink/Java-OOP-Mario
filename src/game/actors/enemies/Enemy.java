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

    protected final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour


    public Enemy(String name, char displayChar, int hitPoints, List<Integer> positionList, List<Behaviour> behaviours){
        super(name, displayChar, hitPoints);
        this.addCapability(Status.CANNOT_ENTER_FLOOR);
        this.addCapability(Status.HOSTILE_TO_PLAYER);

        //add all of the behaviours to the behaviour list
        //eg  this.behaviours.put(7, new GoToFountainBehaviour());
        for (Behaviour behaviour : behaviours){
            int index = behaviours.indexOf(behaviour);
            this.behaviours.put(positionList.get(index),behaviour);

        }

    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(super.allowableActions(otherActor, direction, map));
        return actions;
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        alsoDoThisWhenTicked();


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
