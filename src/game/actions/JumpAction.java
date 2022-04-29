package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.HigherGround;
import game.Wall;

public class JumpAction extends Action {

    private HigherGround highGround;
    private Actor player;
    private Location location;
    private String direction;

    public JumpAction(Location location, Actor actor, String direction, HigherGround ground){
        this.location = location;
        this.player = actor;
        this.direction = direction;
        this.highGround = ground;
    }

    @Override
    public String execute(Actor actor, GameMap map){

        if(Math.random() <= highGround.getSuccessRate()){
            MoveActorAction move = new MoveActorAction(this.location, this.direction);
            move.execute(actor,map);
            return "Jumped to " + highGround.getHighGroundName() + " " + location.x() + " " +  location.y() + " successfully!";
        }
        else {
            actor.hurt(highGround.getFallDamage());
            return "Jump to " + this.direction  + " failed! Lost " + highGround.getFallDamage() + " health!";
        }
    }



    @Override
    public String menuDescription(Actor actor) {

        return "Jump to " + highGround.getHighGroundName() + " (" + location.x() + " " + location.y() + ")";
    }
}
