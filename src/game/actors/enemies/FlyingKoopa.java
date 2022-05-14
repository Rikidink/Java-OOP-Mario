package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.behaviours.*;

public class FlyingKoopa extends Koopa {

    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        this.behaviours.put(10, new FlyingWanderBehaviour());
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(super.allowableActions(otherActor, direction, map));

        if (this.hasCapability(Status.FOLLOWING)) {
            behaviours.put(9, new FlyingFollowBehaviour(otherActor));
        }
        return actions;
    }
}
