package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public abstract class DamagingGround extends Ground {
    int damageAmount;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public DamagingGround(char displayChar, int damageAmount) {
        super(displayChar);
        this.damageAmount = damageAmount;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Actor mario = location.getActor();

        if(location.containsAnActor()){
            mario.hurt(damageAmount);
            if(!location.getActor().isConscious()){
                ActionList dropActions = new ActionList();
                // drop all items
                for (Item item : mario.getInventory())
                    dropActions.add(item.getDropAction(mario));
                for (Action drop : dropActions)
                    drop.execute(mario, location.map());
                // remove actor
                location.map().removeActor(mario);
            }
        }
    }
}
