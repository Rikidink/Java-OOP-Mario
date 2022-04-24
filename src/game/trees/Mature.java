package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.actors.Koopa;
import game.actors.Player;

public class Mature extends Tree {

    public Mature(Actor mario){
        super('T');
        this.mario = mario;
    }


    // TODO: Need to add grow sprout in surrounding area functionality

    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        if(Math.random() <= 0.20){
            location.setGround(new Dirt());
        }
        else {
            if(Math.random() <= 0.15){
                if(!location.containsAnActor()){
                    location.addActor(new Koopa(mario));
                }
            }
        }

    }
}
