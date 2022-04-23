package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

public class Sprout extends Tree {

    public Sprout(){
        super('+');
        
    }

    // TODO: Need to figure out how to get mario object into goomba constructor so that goomba moves around

    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        if(turnCount == 10){
            location.setGround(new Sapling());
        }
        else {
            if(Math.random() <= 0.10){
                if(!location.containsAnActor()){
                    location.addActor(new Goomba());
                }
            }
        }
    }
}
