package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A nasty little ground type which hurts a bit
 */
public class Spores extends DamagingGround {
    int timeRemaining;

    /**
     * Constructor.
     */
    public Spores() {
        super(':',5);
        timeRemaining = 10;
    }

    @Override
    public void tick(Location location) {
        timeRemaining--;
        if (timeRemaining == 0) {
            location.setGround(new Dirt());
        }
    }
}
