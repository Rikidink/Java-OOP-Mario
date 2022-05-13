package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;


public class PowerWater extends StorableFood implements Consumable{


    /***
     * Constructor.
     */
    public PowerWater() {
        super("Power Water", 'w', false);
    }


    @Override
    public void otherThingsToDoWhenConsumed(Actor actor, GameMap map) {
        //add power to play
        super.addAction(new ConsumeAction(this, " drinks some extremely powerful water"));

        //remove item from existence
        actor.removeItemFromInventory(this);
        map.locationOf(actor).removeItem(this);
        super.setHasBeenConsumed(true);



    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks some extremely powerful water";
    }


    @Override
    public String getMenuDescriptionText() {
        return " drinks some extremely powerful water";
    }
}
