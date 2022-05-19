package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ReanimateAction;
import game.behaviours.Behaviour;

import java.util.List;

public class Corpse extends Enemy{

    public Corpse(List<Integer> positionList, List<Behaviour> behaviours) {
        super("Corpse", '@', 9001, positionList, behaviours);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.CAN_REANIMATE)) {
            actions.add(new ReanimateAction(this));
        }
        return actions;
    }
}
