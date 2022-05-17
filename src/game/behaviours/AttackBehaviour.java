package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
import game.actions.FireAttackAction;

/**
 * Find out if something can be attacked
 *
 */
public class AttackBehaviour implements Behaviour{

    private final Actor target;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public AttackBehaviour(Actor target) {
        this.target = target;
    }


    /**
     * gets the action the actor does (attack or not)
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return null or some info about the attack
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        if (distance(here, there)) {
            String str_direction =  direction(here, there);
            if (actor.hasCapability(Status.CAN_FIRE_ATTACK)) {
                return new FireAttackAction(target, str_direction);
            }
            else {
                return new AttackAction(target, str_direction);
            }
                }
        return null;
    }

    /**
     * Work out if the max xy distance between a and b <= 1 (ie attack can occur)
     *
     * @param attacker an actor
     * @param target another actor
     * @return are the two actors close enough to attack?
     */
    private boolean distance(Location attacker, Location target) {
        int distx = Math.abs(attacker.x() - target.x());
        int disty = Math.abs(attacker.y() - target.y());

        return Math.max(distx, disty) <= 1;
    }

    /**
     * Find the direction from which the attack occurs
     *
     * @param attacker from actor
     * @param target to actor
     * @return the direction, eg North-East
     **/
    private String direction(Location attacker, Location target) {
        String vertical = "?";
        String horizontal = "?";
        //get East/West
        int distx = attacker.x() - target.x();

        if (distx < 0) { // the higher a or b the further right they are
            vertical = "West";
        }
        else if(distx > 0) {
            vertical = "East";
        }

        //get North/South
        int disty = attacker.y() - target.y();
        if (disty < 0) { //higher a or b the lower down they are
            horizontal = "North";
        }
        else if(disty > 0) {
            horizontal = "South";
        }

        if (horizontal.equals("?")) {
            if (vertical.equals("?")) {
                return "Directly on top of you!!??";
            }
            else {
                return vertical;
            }
        }
        else {
            if (vertical.equals("?")) {
                return horizontal;
            }
            else {
                return  vertical + "-" + horizontal;
            }
        }
    }
}
