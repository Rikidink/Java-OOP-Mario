package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.Wallet;
import game.actions.fountain.DrinkAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Bottle extends Item{

    /**
     * what drinks bottle contains
     */
    private Stack<DrinkAction> bottleContents;

    private static Bottle instance;

    /**
     * Get the singleton instance of bottle
     *
     * @return Bottle singleton instance
     */
    public static Bottle getInstance(){
        if(instance == null){
            instance = new Bottle();
        }
        return instance;
    }

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Bottle() {
        super("Bottle", 'U', false);
        this.addCapability(Status.CAN_BE_REFILLED);
        this.addCapability(Status.CAN_FILL_BOTTLE);
        bottleContents = new Stack<>();
    }

    public void addConsumableToBottle(DrinkAction drinkAction) {
        bottleContents.push(drinkAction);
    }

    /**
     * get the current drink action
     * @return the currect drink action
     */
    public DrinkAction getCurrentDrinkAction(){
        if (bottleContents.empty()){
            return null;
        }
        else {
            return bottleContents.peek();
        }
    }

    public void consumeFromBottle() {
        bottleContents.pop();
    }

    public Stack<DrinkAction> getBottleContents() {
        return bottleContents;
    }

}
