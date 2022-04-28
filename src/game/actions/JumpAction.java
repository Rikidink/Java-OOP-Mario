package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.HigherGround;
import game.Wall;

public class JumpAction extends Action {

    // dont really know what to do with this
    private HigherGround highGround;
    private Actor player;
    private Location location;
    private String direction;

    public JumpAction(Location location, Actor actor, String direction){
        this.location = location;
        this.player = actor;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map){
        MoveActorAction move = new MoveActorAction(this.location, direction);
        move.execute(actor, map);
        return "Jumped";
    }



    @Override
    public String menuDescription(Actor actor) {

        return "Jump to wall";
    }
}
