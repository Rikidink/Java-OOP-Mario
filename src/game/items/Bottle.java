package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.fountain.DrinkAction;

import java.util.HashMap;

public class Bottle extends Item{

    /**
     * what drinks bottle contains, is used like a stack
     */
    HashMap<Integer, DrinkAction> storage = new HashMap<Integer, DrinkAction>();

    /**
     * How large the stack of the bottle storage is. This is used to retrieve the latest element
     */
    private int storage_counter = 0;

    /**
     * The current action
     */
    private DrinkAction currentDrinkAction;


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




    public void addConsumableToBottle(DrinkAction drinkAction) {

        if (currentDrinkAction == null) {
            currentDrinkAction = drinkAction;
        } else {
            storage_counter++;
            storage.put(storage_counter, currentDrinkAction);
            currentDrinkAction = drinkAction;

        }
    }


    /**
     * reduces the consumable stack by one
     * @param storableFood
     */
    private void reduceConsumableStack(){
        currentDrinkAction =  storage.remove(storage_counter);
        storage_counter --;
        //removeAction(currentDrinkAction);


    }

    /**
     * get the current drink action
     * @return the currect drink action
     */
    public DrinkAction getCurrentDrinkAction(){

        if (currentDrinkAction == null){
            return null;
        } else if (currentDrinkAction.getHasBeenUsed() == true){
            reduceConsumableStack();
        }
        return currentDrinkAction;
    }

}
