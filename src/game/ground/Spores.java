package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Spores extends Ground {
    /**
     * Constructor.
     *
     */
    public Spores() {
        super(':');
    }

    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            actor.hurt(5);
        }
    }
}
