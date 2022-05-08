package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Goomba;

/**
 * Sprout; a child class of tree
 *
 */
public class Sprout extends Tree {

    /**
     * Constructor to create a sprout object
     *
     * @param x x position of sprout
     * @param y y position of sprout
     */
    public Sprout(int x, int y){
        super('+', x, y, 0.9, 10, "Sprout");
        
    }

    /**
     * Function repeats with every turn:
     * After 10 turns the sprout becomes a sapling
     * Has 10 percent chance to spawn a goomba at the Sprout
     *
     * @param location The location of the Ground (i.e: sprout)
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        // replaced with sapling after 10 turns
        if(turnCount == 10){
            location.setGround(new Sapling(location.x(), location.y()));
        }
        // 10 percent chance to spawn a goomba on the sprout
        else {
            if(Math.random() <= 0.1){
                if(!location.containsAnActor()){
                    location.addActor(new Goomba());
                }
            }
        }
    }

}
