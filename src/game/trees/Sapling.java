package game.trees;

import edu.monash.fit2099.engine.positions.Location;

public class Sapling extends Tree {

    public Sapling(){
        super('t');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        if(turnCount == 10){
            location.setGround(new Mature());
        }
        else {
            if(Math.random() <= 0.10){
                // ADD ITEM SHOULD BE COIN HERE:
                // location.addItem();
            }
        }

    }
}
