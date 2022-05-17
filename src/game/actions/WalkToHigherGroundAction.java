package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Dirt;
import game.items.Coin;

/**
 * This is what happenes when you move while being affected by a power star
 */
public class WalkToHigherGroundAction extends MoveActorAction {
    /**
     * Constructor
     *
     * @param moveToLocation    location being moved to
     * @param direction         direction being moved in
     */
    public WalkToHigherGroundAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    /**
     * Allow the Actor to be moved.
     * Overrides Action.execute()
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        moveToLocation.setGround(new Dirt()); //remove the high ground
        moveToLocation.addItem(new Coin(5,moveToLocation.x(), moveToLocation.y())); //spawn a coin
        return menuDescription(actor);
    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return      a String, e.g. "Player moves east"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves " + direction + " and obliterates the high ground in its path";
    }
}
