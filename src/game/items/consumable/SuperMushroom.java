package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.ConsumeAction;
import game.items.Buyable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A magic mushroom that isn't illegal. It allows actors to jump pretty well among other things
 */
public class SuperMushroom extends Item implements Consumable, Buyable {

    /**
     * An array list of all of the statuses an actor will gain when consuming this item
     */
    ArrayList<Status> statusList;

    /**
     * Constructor
     *
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        statusList = new ArrayList<>(Arrays.asList(Status.TALL)); //set the list of status to update
        super.addAction(new ConsumeAction(this, " consumes the Super Mushroom"));
    }

    /**
     * A list of other things to be done when the item is consumed
     *
     * @param actor the actor to be effected by the consumption
     * @param map   the map this is occuring on
     * @see Consumable#otherThingsToDoWhenConsumed(Actor actor, GameMap map)
     */
    @Override
    public void otherThingsToDoWhenConsumed(Actor actor, GameMap map) {
        //make sure the item is deleted
        actor.removeItemFromInventory(this);
        map.locationOf(actor).removeItem(this);

        //specific stuff
        actor.increaseMaxHp(50);
    }

    /**
     * Adds all of the statuses stored in the item to an actor (who consumed it presumably)
     *
     * @param actor the actor to add the statuses to
     */
    public void addStatuses(Actor actor){
        for (Status status: statusList){
            actor.addCapability(status);
        }
    }

    /**
     * Removes all of the capabilities given to an actor by an item
     *
     * @param actor the actor to remove the statuses from
     */
    public void removeStatuses(Actor actor){
        for (Status status: statusList){
            actor.removeCapability(status);
        }
    }

    /**
     * The description for the menu when it is consumed
     * @param actor The Actor that can take the action
     * @return      The sting of the menu description to output
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the Super Mushroom";
    }

    /**
     * what to do when something is bought
     *
     * @param actor the actor to be effected
     * @see Buyable#onBuy(Actor actor)
     */
    @Override
    public void onBuy(Actor actor) {
        actor.addItemToInventory(this);
        this.togglePortability();
    }
}
