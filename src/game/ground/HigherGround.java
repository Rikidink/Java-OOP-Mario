package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;


/**
 * An abstract class that denotes that a child can be jumped to
 *
 */
abstract public class HigherGround extends Ground {

    /**
     * The success rate of a jump on a given high ground
     *
     */
    protected double successRate;

    /**
     * The fall damage taken if the jump to the high ground failed
     *
     */
    protected int fallDamage;

    /**
     * A string of the name of the high ground
     *
     */
    protected String highGroundName;


    /**
     * Base abstract constructor for the HigherGround class
     *
     * @param displayChar the displayed letter of the class on the game map
     */
    public HigherGround(char displayChar, double successRate, int fallDamage, String highGroundName) {
        super(displayChar);
        this.successRate = successRate;
        this.fallDamage = fallDamage;
        this.highGroundName = highGroundName;
    }

    /**
     * Method to return the success rate of the jump
     *
     * @return the success rate
     */
    public double getSuccessRate(){
        return successRate;
    }


    /**
     * Method to return the fall damage received for a failed jump
     *
     * @return the fall damage
     */
    public int getFallDamage(){
        return fallDamage;
    }

    /**
     * Method to return the high ground name
     *
     * @return string of the high ground name
     */
    public String getHighGroundName(){
        return highGroundName;
    }

    /**
     * Method that adds the jump action to higher ground objects
     *
     * @param actor the Actor acting; i.e: the player
     * @param location the current Location of the higher ground
     * @param direction the direction of the Ground from the Actor
     * @return the list of actions for the higher ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if(!location.containsAnActor()){
            if (actor.hasCapability(Status.HAS_EATEN_POWER_STAR)){
                actions.add(new PowerStarMoveActorAction(location,direction));
            }
            else {
                actions.add(new JumpAction(location, direction, (HigherGround) location.getGround()));
            }
        }
         return actions;
    }
}
