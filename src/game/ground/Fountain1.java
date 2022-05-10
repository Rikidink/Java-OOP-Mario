package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.items.consumable.ConsumeAction;

/**
 * Fountains are able to refill bottles and be drunk from to replenish health
 */
public class Fountain1 extends Ground {

    private char displayChar;
    private String type;



    public Fountain1(String type) {
        //set a placeholder display character while type is worked out
        super('_');
        if (type == "Health") {
            displayChar = 'H';
        } else if (type == "Power") {
            displayChar = 'A';
        } else {
            displayChar = '_'; // a failsafe

        }

        super.setDisplayChar(displayChar);
        this.type = type;

    }


    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if(!location.containsAnActor()){
            if (actor.hasCapability(Status.WALK_TO_HIGHER_GROUND)){
                actions.add(new WalkToHigherGroundAction(location,direction));
            }
            else {
                actions.add(new JumpAction(location, direction, (HigherGround) location.getGround()));
            }
        }
        return actions;
    }
}
