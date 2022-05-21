package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Ground that is on fire
 */
public class FireGround extends DamagingGround {
    int timeRemaining;

    /**
     * Constructor.
     */
    public FireGround() {
        super('v', 20);
        timeRemaining = 3;
    }

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
