package game.ground;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

public class PipeSpawner {

    public static void spawnPipes(GameMap gameMap, GameMap lavaMap) {
        Random rand = new Random();

        int pipeAmount = 6;
        int mapHeight = gameMap.getYRange().max();
        int mapWidth = gameMap.getXRange().max();

        while (pipeAmount > 0) {
            int randomHeight = rand.nextInt((mapHeight) + 1);
            int randomWidth = rand.nextInt((mapWidth) + 1);

            gameMap.at(randomWidth, randomHeight).setGround(new Pipe(lavaMap));
            pipeAmount -= 1;
        }
    }
}
