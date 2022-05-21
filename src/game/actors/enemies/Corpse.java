package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ReanimateAction;
import game.actors.Zonbi;
import game.behaviours.Behaviour;

import java.util.List;

public class Corpse extends Enemy{

    int timeRemaining;

    public Corpse() {
        super("Corpse", '@', 9001, 0, "doesn't attack");
        timeRemaining = 5;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.CAN_REANIMATE)) {
            actions.add(new ReanimateAction(this));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (timeRemaining == 0) {
            map.removeActor(this);
        }
        timeRemaining--;

        return super.playTurn(actions, lastAction, map, display);
    }
}
