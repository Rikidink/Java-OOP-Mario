package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * The class JumpAction which performs a jump to a higher ground
 *
 */
public class JumpAction extends Action {

    /**
     * An attribute of the HigherGround class
     *
     */
    private HigherGround highGround;

    /**
     * Attribute of the location to be jumped to
     *
     */
    private Location location;

    /**
     * The direction of the location to be jumped to
     *
     */
    private String direction;

    /**
     * Method for creating the jump action
     *
     * @param location location of the high ground to be jumped to
     * @param direction the direction of the high ground to be jumped to
     * @param ground the HigherGround that will be jumped to
     */
    public JumpAction(Location location, String direction, HigherGround ground){
        this.location = location;
        this.direction = direction;
        this.highGround = ground;
    }

    /**
     * Method that runs if the player chooses to jump
     *
     * @param actor The actor performing the action, i.e: the player
     * @param map The map the actor is on.
     * @return a string showing details of a successful/failed jump
     */
    @Override
    public String execute(Actor actor, GameMap map){
        // uses the success rate from high ground objects
        if(actor.hasCapability(Status.TALL) || Math.random() <= highGround.getSuccessRate()){
            MoveActorAction move = new MoveActorAction(this.location, this.direction);
            move.execute(actor,map);
            return "Jumped to " + highGround.getHighGroundName() + " " + location.x() + " " +  location.y() + " successfully!";
        }
        // uses fall damage from high ground objects
        else {
            actor.hurt(highGround.getFallDamage());
            return "Jump to " + this.direction  + " failed! Lost " + highGround.getFallDamage() + " health!";
        }
    }

    /**
     * Method that prints the description for the action in the menu
     *
     * @param actor The actor performing the jump action.
     * @return the menu description of jump action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Jump to " + highGround.getHighGroundName() + " (" + location.x() + " " + location.y() + ") at " + direction;
    }
}
