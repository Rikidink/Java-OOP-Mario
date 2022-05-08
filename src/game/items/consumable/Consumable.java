package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;

/**
 * All items that can be consumed
 */
public abstract class Consumable extends Item {

    /**
     * A list of all the statuses
     */
    protected ArrayList<Enum> statusList;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Consumable(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);

    }


    /**
     * Adds all of the statuses stored in the item to an actor (who consumed it presumably)
     */
    public void addStatuses(Actor actor){
        for (Enum status: statusList){
            actor.addCapability(status);
        }
    }

    /**
     * Set the statuslist
     * @param statusList a list of all the statuses consuming the item will grant the actor
     */
    protected void setStatusList(ArrayList<Enum> statusList){
        this.statusList = statusList;
    }


    /**
     * Gets the status list
     * @return  a list of all the statuses consuming the item will grant the actor
     */
    public ArrayList<Enum> getStatusList() {
        return statusList;
    }


    /**
     * A list of other things to be done when the item is consumed
     */
    abstract public void otherThingsToDoWhenConsumed(Actor actor, GameMap map);


    /**
     * The description for the menu when it is consumed
     * @param actor
     * @return
     */
    abstract public String menuDescription(Actor actor);


}
