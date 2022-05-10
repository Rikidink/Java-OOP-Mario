package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.items.consumable.ConsumeAction;

abstract class Fountain extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);

    }

    @Override
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
