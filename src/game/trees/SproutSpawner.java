package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;

import java.util.Random;

public class SproutSpawner {

    private Location location;

    public SproutSpawner(){

    }

    public static void generateSprouts(Actor mario, GameMap gameMap){
        Random rand = new Random();
        int sproutAmount = 15;
        int mapHeight = gameMap.getYRange().max();
        int mapWidth = gameMap.getXRange().max();
        // make sure to +1 in the random value

        while (sproutAmount > 0) {
            int randomHeight = rand.nextInt((mapHeight) + 1);
            int randomWidth = rand.nextInt((mapWidth) + 1);

            if(gameMap.at(randomWidth, randomHeight).getGround() instanceof Dirt){
                gameMap.at(randomWidth, randomHeight).setGround(new Sprout (mario, randomWidth, randomHeight));
                sproutAmount -= 1;
            }
        }



    }
}
