package game.ground;

import edu.monash.fit2099.engine.positions.GameMap;
import game.trees.Sprout;

import java.util.Random;

public class SoftGroundSpawner {

    public static void generateSoftGround(GameMap gameMap) {
        Random rand = new Random();

        int softGroundAmount = 10;
        int mapHeight = gameMap.getYRange().max();
        int mapWidth = gameMap.getXRange().max();

        while (softGroundAmount > 0) {
            int randomHeight = rand.nextInt((mapHeight) + 1);
            int randomWidth = rand.nextInt((mapWidth) + 1);
            gameMap.at(randomWidth, randomHeight).setGround(new SoftGround());
            softGroundAmount -= 1;
        }
    }
}
