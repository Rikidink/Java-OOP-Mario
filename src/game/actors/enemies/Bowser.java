package game.actors.enemies;

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
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.GoToFountainBehaviour;
import game.items.Key;
import game.reset.Resettable;

import java.util.Arrays;

public class Bowser extends Enemy implements Resettable {
    GameMap bowserMap;

    /**
     * Constructor.
     *
     */
    public Bowser(GameMap map) {
        super("Bowser", 'B', 500, 80, "punches");
        addItemToInventory(new Key());
        this.addCapability(Status.CAN_FIRE_ATTACK);
        bowserMap = map;
        registerInstance();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Exit exit : map.locationOf(this).getExits()) {
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                behaviours.put(8, new AttackBehaviour(exit.getDestination().getActor()));
                behaviours.put(9, new FollowBehaviour(exit.getDestination().getActor()));
            }
        }

        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance(GameMap map) {
        //move back to original position, heal to max health, stop following.
        bowserMap.moveActor(this, bowserMap.at(31, 7));
        heal(getMaxHp());
        removeCapability(Status.FOLLOWING);
        behaviours.clear();
    }
}
