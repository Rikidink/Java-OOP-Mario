package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public class ResetAction extends Action {

    protected static final String hotkey = "r";

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(Status.CAN_RESET);
        return "The game has been reset.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }

    @Override
    public String hotkey() {
        return hotkey;
    }
}
