package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;

public class PowerWater extends Item implements Consumable, StorableFood{


    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public PowerWater(String name, char displayChar, boolean portable) {
        super("Power Water", 'w', false);
    }


    @Override
    public void otherThingsToDoWhenConsumed(Actor actor, GameMap map) {
        //add power to play
        super.addAction(new ConsumeAction(this, " drinks some extremely powerful water"));

        //remove item from existence
        actor.removeItemFromInventory(this);
        map.locationOf(actor).removeItem(this);
        setHasBeenConsumed(true);



    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks some extremely powerful water";
    }

    @Override
    public boolean setHasBeenConsumed(boolean status) {
        return false;
    }

    @Override
    public boolean getConsumptionStatus() {
        return false;
    }
}
