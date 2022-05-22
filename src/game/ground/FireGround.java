package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Ground that is on fire
 */
public class FireGround extends DamagingGround {

    /**
     * remaining time that this FireGround will exist for
     */
    int timeRemaining;

    /**
     * Constructor.
     */
    public FireGround() {
        super('v', 20);
        timeRemaining = 3;
    }

    /**
     * Ground can also experience the joy of time.
     *
     * @param location The location of the Ground
     * @see Ground#tick(Location location)
     */
    @Override
    public void tick(Location location) {
        if (timeRemaining == 0) {
            location.setGround(new Dirt());
        }
        else {
            super.tick(location);
            }
            timeRemaining -= 1;
    }
}
