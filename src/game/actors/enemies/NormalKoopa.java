package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

public class NormalKoopa extends Koopa {

    public NormalKoopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(10, new WanderBehaviour());
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(super.allowableActions(otherActor, direction, map));

        if (this.hasCapability(Status.FOLLOWING)) {
            behaviours.put(9, new FollowBehaviour(otherActor));
        }
        return actions;
    }
}
