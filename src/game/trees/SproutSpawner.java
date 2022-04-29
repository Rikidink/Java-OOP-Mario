package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

import java.util.Random;

/**
 * SproutSpawner; class that is responsible for spawning sprouts randomly on the game map
 */
public class SproutSpawner {

    /**
     * Constructor for SproutSpawner
     */
    public SproutSpawner(){

    }

    /**
     * Method to place an x amount of sprouts randomly on the game map
     * @param mario the player
     * @param gameMap the game map sprouts spawn on
     */
    public static void generateSprouts(Actor mario, GameMap gameMap){
        Random rand = new Random();

        // amount of sprouts that should be spawned on the map
        int sproutAmount = 30;
        int mapHeight = gameMap.getYRange().max();
        int mapWidth = gameMap.getXRange().max();

        // keeps adding sprouts if not enough sprouts are made
        while (sproutAmount > 0) {
            int randomHeight = rand.nextInt((mapHeight) + 1);
            int randomWidth = rand.nextInt((mapWidth) + 1);

            // checks to see if the randomly selected ground is fertile, and spawns a sprout if true
            if(gameMap.at(randomWidth, randomHeight).getGround().hasCapability(Status.FERTILE_GROUND)){
                gameMap.at(randomWidth, randomHeight).setGround(new Sprout (mario, randomWidth, randomHeight));
                sproutAmount -= 1;
            }
        }



    }
}
