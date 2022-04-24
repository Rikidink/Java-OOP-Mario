package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

public class Sapling extends Tree {

    public Sapling(){
        super('t');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        if(turnCount == 10){
            location.setGround(new Mature(mario));
        }
        else {
            if(Math.random() <= 0.10){
                location.addItem(new Coin(20,location.x(), location.y()));
            }
        }

    }
}
