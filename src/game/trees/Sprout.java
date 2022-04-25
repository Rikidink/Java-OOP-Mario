package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

public class Sprout extends Tree {


    public Sprout(Actor mario){
        super('+');
        this.mario = mario;
        
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        if(turnCount == 10){
            location.setGround(new Sapling());
        }
        else {
            if(Math.random() <= 0.1){
                if(!location.containsAnActor()){
                    location.addActor(new Goomba(mario));
                }
            }
        }
    }
}
