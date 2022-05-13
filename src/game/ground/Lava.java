package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Lava extends Ground {

    private final int lavaDamage = 15;

    /**
     * Constructor.
     */
    public Lava() {
        super('L');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.LAVA_WALK);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Actor mario = location.getActor();

        if(location.containsAnActor()){
            mario.hurt(lavaDamage);
            System.out.println("Mario has taken " + lavaDamage + " damage from lava!");
            if(!location.getActor().isConscious()){
                ActionList dropActions = new ActionList();
                // drop all items
                for (Item item : mario.getInventory())
                    dropActions.add(item.getDropAction(mario));
                for (Action drop : dropActions)
                    drop.execute(mario, location.map());
                // remove actor
                location.map().removeActor(mario);
                System.out.println("Mario burnt in the lava!");
            }
        }
    }
}