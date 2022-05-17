package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.FireGround;

public class FireAttackAction extends AttackAction{

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction
     */
    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).setGround(new FireGround());
        return super.execute(actor, map);
    }
}
