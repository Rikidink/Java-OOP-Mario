package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;

/**
 * All items that can be consumed
 */
public interface Consumable{

    /**
     * A list of all the statuses
     */
    ArrayList<Enum> statusList = new ArrayList<Enum>();

    /**
     * Adds all of the statuses stored in the item to an actor (who consumed it presumably)
     *
     * @param actor The Actor to add the Statuses to
     */
    default void addStatuses(Actor actor){
        for (Enum status: statusList){
            actor.addCapability(status);
        }
    }

    /**
     * Removes all of the capabilities given to an actor by an item
     *
     * @param actor The Actor to remove the Statuses from
     */
    default void removeStatuses(Actor actor){
        for (Enum status: statusList){
            actor.removeCapability(status);
        }
    }

    /**
     * Set the statuslist
     * @param statusList a list of all the statuses consuming the item will grant the actor
     */
    default void setStatusList(ArrayList<Enum> statusList){
        statusList = statusList;
    }

    /**
     * Gets the status list
     * @return  a list of all the statuses consuming the item will grant the actor
     */
    default ArrayList<Enum> getStatusList() {
        return statusList;
    }

    /**
     * A list of other things to be done when the item is consumed
     *
     * @param actor the actor to be effected by the consumption
     * @param map   the map this is occuring on
     */
    public void otherThingsToDoWhenConsumed(Actor actor, GameMap map);

    /**
     * The description for the menu when it is consumed
     * @param actor The Actor that can take the action
     * @return      The sting of the menu description to output
     */
    public String menuDescription(Actor actor);

    /**
     * A check for if the action was able to happen. Intended for cases when an action can be access multiple times per turn
     * but might only be intended to be access once
     *
     * @return if the action was a success, ie if it cna happen
     */
    default boolean actionSuccess(){
        return true;
    }

}
