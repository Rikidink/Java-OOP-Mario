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

        // TODO: HAVE TO RETRIEVE THE GROUND'S SUCCESSRATE AND FALLDAMAGE
        // TODO: DONT SHOW MENU DESCRIPTION FOR HIGH GROUND PLAYER IS ON: DONT ADD JUMPACTION OBJECT IF PLAYER ON LOCATION, CHECK IN THE GROUND CLASS ACTIONLIST

        if(Math.random() <= highGround.getSuccessRate()){
            MoveActorAction move = new MoveActorAction(this.location, this.direction);
            move.execute(actor,map);
            return "Jumped to " + this.direction + " successfully!";
        }
        else {
            actor.hurt(highGround.getFallDamage());
            return "Jump to " + this.direction  + " failed! Lost " + 20 + " health!";
        }
    }



    @Override
    public String menuDescription(Actor actor) {

        return "Jump to high ground";
    }
}
