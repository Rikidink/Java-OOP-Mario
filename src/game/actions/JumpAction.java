package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.HigherGround;
import game.Wall;

public class JumpAction extends Action {

    // dont really know what to do with this
    private HigherGround highGround;
    private Actor player;

    public JumpAction(){
    }

    @Override
    public String execute(Actor actor, GameMap map){

        return "Jumped";
    }



    @Override
    public String menuDescription(Actor actor) {

        return "Jump to wall";
    }
}
