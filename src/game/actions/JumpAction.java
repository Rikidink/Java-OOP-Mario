package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.HigherGround;

public class JumpAction extends Action {

    private HigherGround highGround;

    @Override
    public String execute(Actor actor, GameMap map){
        return "Placeholder";
    }

    @Override
    public String menuDescription(Actor actor) {

        return "Placeholder";
    }
}
