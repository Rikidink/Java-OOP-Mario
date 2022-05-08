package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;

import java.util.ArrayList;
import java.util.Arrays;

public class SuperMushroom extends Item implements Consumable{

    /**
     * A consume action which is updated according to the time remaining
     */
    private ConsumeAction consumeAction = new ConsumeAction(this, " consumes the Super Mushroom");


    /**
     * An array list of all of the statuses an actor will gain when consuming this item
     */
    ArrayList<Enum> statusList;


    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        statusList = new ArrayList<Enum>(Arrays.asList(Status.TALL)); //set the list of status to update
        super.addAction(consumeAction);
    }

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
     */
    public void addStatuses(Actor actor){
        for (Enum status: statusList){
            actor.addCapability(status);
        }
    }

    /**
     * Removes all of the capabilities given to an actor by an item
     */
    public void removeStatuses(Actor actor){
        for (Enum status: statusList){
            actor.removeCapability(status);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the Super Mushroom";
    }






}
