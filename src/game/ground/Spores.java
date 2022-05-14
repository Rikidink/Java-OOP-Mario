package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Spores extends Ground {
    int timeRemaining;

    /**
     * Constructor.
     *
     */
    public Spores() {
        super(':');
        timeRemaining = 10;
    }

    @Override
    public void tick(Location location) {
        timeRemaining--;
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            actor.hurt(5);
        }
        if (timeRemaining == 0) {
            location.setGround(new Dirt());
        }
    }
}
