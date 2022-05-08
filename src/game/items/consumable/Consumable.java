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
     */
    default void addStatuses(Actor actor){
        for (Enum status: statusList){
            actor.addCapability(status);
        }
    }

    /**
     * Removes all of the capabilities given to an actor by an item
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
     */
    void otherThingsToDoWhenConsumed(Actor actor, GameMap map);


    /**
     * The description for the menu when it is consumed
     * @param actor
     * @return
     */
    String menuDescription(Actor actor);


}
