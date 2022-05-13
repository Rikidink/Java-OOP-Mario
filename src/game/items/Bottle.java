package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.items.consumable.ConsumeAction;
import game.items.consumable.StorableFood;

import java.util.HashMap;

public class Bottle extends Item{

    /**
     * what drinks bottle contains, is used like a stack
     */
    HashMap<Integer, StorableFood> storage = new HashMap<Integer, StorableFood>();

    /**
     * How large the stack of the bottle storage is. This is used to retrieve the latest element
     */
    private int storage_counter = 0;

    /**
     * The current consumable active
     */
    private StorableFood currentStorable;


    /**
     * The current action possible (ie what you can currently consume)
     */

    private Action currentAction = new ConsumeAction(null,null);

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Bottle() {
        super("Bottle", 'U', false);
        this.addCapability(Status.CAN_BE_REFILLED);
    }






    public void addConsumableToBottle(StorableFood storableFood){
        storage_counter++;
        storage.put(storage_counter, storableFood);

        if (storage_counter == 1 && currentStorable == null){ //the case where this is the only item
            reduceConsumableStack();
        }


    }


    /**
     * reduces the consumable stack by one
     * @param storableFood
     */
    private void reduceConsumableStack(){
        currentStorable =  storage.remove(storage_counter);
        storage_counter --;
        super.removeAction(currentAction);

        if (currentStorable != null){ //replace the current action
            currentAction = new ConsumeAction(currentStorable, currentStorable.getMenuDescriptionText());
            super.addAction(currentAction);
            //todo: fix the weird menu description method spam
        }

    }


}
