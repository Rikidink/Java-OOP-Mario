package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;

public class NormalKoopa extends Koopa {

    public NormalKoopa() {
        super("Koopa", 'K', 100);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(super.allowableActions(otherActor, direction, map));

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(this.hasCapability(Status.IS_DORMANT)) || otherActor.getWeapon().toString().equals("Wrench")) {
            actions.add(new AttackAction(this, direction)); //todo: add check for if dormant - remove from alloiwable acitons
        }

        if (this.hasCapability(Status.FOLLOWING)) {
            behaviours.put(9, new FollowBehaviour(otherActor));
            behaviours.put(8, new AttackBehaviour(otherActor));
        }
        return actions;
    }
}
