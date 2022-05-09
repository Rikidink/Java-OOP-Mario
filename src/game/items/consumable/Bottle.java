package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import java.util.ArrayList;

public class Bottle extends Item implements Consumable{

    private String type;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Bottle(String name, char displayChar, boolean portable, String type) {
        super("Power Star", 'U', false);
        this.type = type;
    }


    @Override
    public void otherThingsToDoWhenConsumed(Actor actor, GameMap map) {
        if (type == "Health"){
            actor.heal(50);
        } else if (type == "Power") {
            System.out.println("Added 15 power?");
            //todo: this later

            }
        }



    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks a big sip of " + type + " water";

    }
}
