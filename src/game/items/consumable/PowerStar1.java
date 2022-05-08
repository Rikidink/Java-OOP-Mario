package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import java.util.ArrayList;
import java.util.Arrays;

public class PowerStar1 extends Consumable{

    /**
     * time remaining until item is  destroyed
     */
    private int timeRemaining = 10;

    /**
     * A consume action which is updated according to the time remaining
     */
    private ConsumeAction consumeAction = new ConsumeAction(this, " consumes the Power Star - " + timeRemaining + " turns remaining");


    /**
     * An array list of all of the statuses an actor will gain when consuming this item
     */
    ArrayList<Enum> statusList = new ArrayList<Enum>(Arrays.asList(Status.TALL));

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public PowerStar1() {
        super("Power Star", '*', true);
        super.statusList = new ArrayList<Enum>(Arrays.asList(Status.INVINCIBLE, Status.WALK_TO_HIGHER_GROUND, Status.INSTA_KILLER)); //set the list of status to update
        super.addAction(consumeAction);
    }

    @Override
    public void otherThingsToDoWhenConsumed(Actor actor, GameMap map) {
        //nothing here needed

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
        if (timeRemaining == 0) {
            actor.removeItemFromInventory(this);
        } else {
            super.removeAction(consumeAction);
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
