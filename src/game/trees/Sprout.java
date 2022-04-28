package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.actors.Goomba;

public class Sprout extends Tree {


    public Sprout(Actor mario, int x, int y){
        super('+', x, y);
        this.mario = mario;
        
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        if(turnCount == 10){
            location.setGround(new Sapling(location.x(), location.y()));
        }
        else {
            if(Math.random() <= 0.1){
                if(!location.containsAnActor()){
                    location.addActor(new Goomba(mario));
                }
            }
        }
    }

    @Override
    public void resetInstance(GameMap map) {
        if (Math.random() <= 0.5) {
            map.at(x, y).setGround(new Dirt());
        }
    }

    @Override
    public void registerInstance() {
        super.registerInstance();
    }


}
