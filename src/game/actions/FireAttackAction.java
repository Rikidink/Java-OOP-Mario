package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.FireGround;

/**
 * At attack that leaves a lingering flame on the ground
 */

public class FireAttackAction extends AttackAction{

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction The direction of incoming attack
     */
    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(target).setGround(new FireGround());
        return super.execute(actor, map);
    }
}
