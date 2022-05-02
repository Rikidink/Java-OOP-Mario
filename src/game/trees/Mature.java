package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Dirt;
import game.actors.Status;
import game.enemies.Koopa;
import game.reset.Resettable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Mature; a child class of tree
 *
 */
public class Mature extends Tree {

    /**
     * Constructor to create a mature object
     *
     * @param x x position of mature
     * @param y y position of mature
     */
    public Mature(int x, int y){
        super('T', x, y, 0.7, 30, "Mature");
    }

    /**
     * Function repeats with every turn:
     * Has 20 percent chance to die and be replaced with dirt
     * Every 5 turns, has chance to spawn a sprout on a surrounding fertile ground
     * Has a 15 percent chance to spawn a koopa on the mature
     *
     * @param location The location of the Ground (i.e: mature)
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        // 20 percent chance to get replaced with dirt
        if(Math.random() <= 0.20){
            location.setGround(new Dirt());
        }
        // every 5 turns a sprout spawns in surrounding area
        else if(turnCount % 5 == 0){
            Random rand = new Random();
            // empty array list to store exits that are fertile ground
            ArrayList<Exit> exits = new ArrayList<>();

            // checks if any exits are fertile ground and adds to the exits arraylist above
            for(int i = 0; i < location.getExits().size(); i++){
                if(location.getExits().get(i).getDestination().getGround().hasCapability(Status.FERTILE_GROUND)){
                    exits.add(location.getExits().get(i));
                }
            }
            // if there is fertile ground, choose a random exit in arraylist and spawn sprout
            if(exits.size() > 0){
                int randExitNum = rand.nextInt(exits.size());
                int randX = exits.get(randExitNum).getDestination().x();
                int randY = exits.get(randExitNum).getDestination().y();
                exits.get(randExitNum).getDestination().setGround(new Sprout(randX, randY));
            }
        }
        // 15 percent chance to spawn koopa on the mature location
        else {
            if(Math.random() <= 0.15){
                if(!location.containsAnActor()){
                   location.addActor(new Koopa());
                }
            }
        }
    }

    /**
     * Resets abilities, attributes, and/or items.
     *
     * @see Resettable#resetInstance(GameMap map)
     */
    @Override
    public void resetInstance(GameMap map) {
        if (Math.random() <= 0.5) {
            map.at(x, y).setGround(new Dirt());
        }
    }
}
