package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.consumable.Consumable;
import game.items.consumable.StorableFood;

import java.util.ArrayList;
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


    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Bottle() {
        super("Bottle", 'U', false);
    }



    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        //work out if top item has been consumed
    }


    public void addConsumableToBottle(StorableFood storableFood){
        storage_counter ++;
        storage.put(storage_counter, storableFood);

    }


    public void getConsumableInBottle(StorableFood storableFood){
        //if currentStorable.
        //currentStorable =  storage.remove(storage_counter);

    }


}
