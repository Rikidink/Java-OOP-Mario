package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import java.util.ArrayList;
import java.util.Arrays;

public class PowerStar extends Item implements Consumable{

    /**
     * time remaining until item is  destroyed
     */
    private int timeRemaining = 10;

    /**
     * So it can keep track of the turns after being eaten
     */
    private boolean consumed = false;

    /**
     * A consume action which is updated according to the time remaining
     */
    private ConsumeAction consumeAction = new ConsumeAction(this, " consumes the Power Star - " + timeRemaining + " turns remaining");


    /**
     * An array list of all of the statuses an actor will gain when consuming this item
     */
    ArrayList<Enum> statusList;


    public PowerStar() {
        super("Power Star", '*', true);
        statusList = new ArrayList<Enum>(Arrays.asList(Status.INVINCIBLE, Status.WALK_TO_HIGHER_GROUND, Status.INSTA_KILLER)); //set the list of status to update
        super.addAction(consumeAction);
    }

    @Override
    public void otherThingsToDoWhenConsumed(Actor actor, GameMap map) {
        consumed = true;
        super.togglePortability(); //make it appear as though it doens't exist
        super.removeAction(consumeAction);

        //make sure the item is in the actors inventory - and only there
        actor.removeItemFromInventory(this);
        actor.addItemToInventory(this);
        map.locationOf(actor).removeItem(this);

        timeRemaining = 10; //reset time remaining so it knows when to remove itself from the inventory

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
        return actor + " consumes the Power Star - " + timeRemaining + " turns remaining";
    }



    /**
     * Inform a carried Item of the passage of time.
     * This method is called once per turn, if the Item is being carried.
     * If 10 turns have passed since create the item is removed from the game
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        timeRemaining --;

        if (consumed == false) {
            if (timeRemaining == 0) {
                actor.removeItemFromInventory(this);
            } else {
                consumeAction.setMenuDescriptionText(" consumes the Power Star - " + timeRemaining + " turns remaining");

            }
        } else {
            //when the item has been consumed do this....
            // the flag keeps track of if removing all capabilites is needed
            if (timeRemaining == 0) {
                actor.removeItemFromInventory(this); //delete itself to save space

                if (actor.hasCapability(Status.STAR_FLAG)){
                    removeStatuses(actor);
                }

            } else if (timeRemaining == 1) {
                actor.addCapability(Status.STAR_FLAG );

            } else {
            actor.removeCapability(Status.STAR_FLAG);
        }
        }
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * If 10 turns have passed since create the item is removed from the game
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        timeRemaining--;
        if (timeRemaining == 0) {
            currentLocation.removeItem(this);
        } else {
            consumeAction.setMenuDescriptionText(" consumes the Power Star - " + timeRemaining + " turns remaining");
        }
    }

}
