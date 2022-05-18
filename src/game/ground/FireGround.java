package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class FireGround extends Ground {
    int timeRemaining;

    /**
     * Constructor.
     *
     */
    public FireGround() {
        super('v');
        timeRemaining = 3;
    }

    @Override
    public void tick(Location location) {
        if (timeRemaining == 0) {
            location.setGround(new Dirt());
        }
        else {
            if (location.containsAnActor()) {
                Actor actor  = location.getActor();
                actor.hurt(20);
            }
            timeRemaining -= 1;
        }
    }
}
