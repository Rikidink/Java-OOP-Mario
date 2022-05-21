package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Zonbi;

/**
 * reanimates fallen actors
 */
public class ReanimateAction extends Action {
    Actor target;

    /**
     * Constructor
     * @param target what to reanimate
     */
    public ReanimateAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location spawnLocation = map.locationOf(target);
        map.removeActor(target);
        spawnLocation.addActor(new Zonbi());

        return ("The corpse rises again, a shambling Zonbi to do your bidding.");
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " plunges the necromancy dagger into the corpse, reviving it.");
    }
}
