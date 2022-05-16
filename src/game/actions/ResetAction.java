package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.reset.ResetManager;

/**
 * An Action type for resetting the game
 *
 */
public class ResetAction extends Action {

    protected static final String hotkey = "r";

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(Status.CAN_RESET);
        ResetManager.getInstance().run(map);
        return "The game has been reset.";
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @see Action#hotkey()
     */
    @Override
    public String hotkey() {
        return hotkey;
    }
}
