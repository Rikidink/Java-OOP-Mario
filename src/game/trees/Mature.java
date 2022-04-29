package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.Status;
import game.actors.Koopa;

import java.util.ArrayList;
import java.util.Random;

/**
 * Mature; a child class of tree
 */
public class Mature extends Tree {

    /**
     * The success rate if the player jumps to the mature
     */
    private final double successRate = 0.7;

    /**
     * The fall damage the player receives if the jump to mature fails
     */
    private final int fallDamage = 30;

    /**
     * THe name of the mature as a string
     */
    private final String highGroundName = "Mature";


    /**
     * Constructor to create a mature object
     * @param mario the player
     * @param x x position of mature
     * @param y y position of mature
     */
    public Mature(Actor mario, int x, int y){
        super('T', x, y);
        this.mario = mario;
    }

    /**
     * Function repeats with every turn:
     * Has 20 percent chance to die and be replaced with dirt
     * Every 5 turns, has chance to spawn a sprout on a surrounding fertile ground
     * Has a 15 percent chance to spawn a koopa on the mature
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
                exits.get(randExitNum).getDestination().setGround(new Sprout(mario, location.x(), location.y()));
            }

        }

        // 15 percent chance to spawn koopa on the mature location
        else {
            if(Math.random() <= 0.15){
                if(!location.containsAnActor()){
                   location.addActor(new Koopa(mario));
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

    /**
     * Method to return the success rate
     * @return the success rate
     */
    @Override
    public double getSuccessRate() {
        return successRate;
    }

    /**
     * Method to return the fall damage
     * @return the fall damage
     */
    @Override
    public int getFallDamage() {
        return fallDamage;
    }

    /**
     * Method to return the name of the mature (Mature)
     * @return Mature as a spring
     */
    @Override
    public String getHighGroundName() {
        return highGroundName;
    }
}
