package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;


import game.actors.Player;
import game.actors.Toad;
import game.ground.Dirt;
import game.ground.Floor;
import game.ground.fountain.HealthFountain;
import game.ground.Wall;
import game.items.Bottle;
import game.items.consumable.PowerStar;
import game.items.consumable.SuperMushroom;
import game.trees.*;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor());

		List<String> map = Arrays.asList(
				"..........................................##....................................",
				"............................................#...................................",
				"............................................#...................................",
				".............................................##.................................",
				"...............................................#................................",
				"................................................#...............................",
				"..................................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........................................#____####..............................",
				"........................................#_____###...............................",
				"........................................#______###..............................",
				".........................................#_____###..............................",
				".................................................##.............................",
				"...................................................#............................",
				"....................................................#...........................",
				".....................................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		Actor mario = new Player("Player", 'm', 100);
		world.addPlayer(mario, gameMap.at(42, 10));


		gameMap.at(42, 10).addItem(new PowerStar());
		gameMap.at(42, 10).addItem(new SuperMushroom());
		gameMap.at(44, 11).addActor(new Toad());

		mario.addItemToInventory(new Bottle());

		//add a fountain
		gameMap.at(42, 8).addItem(new HealthFountain());

		// spawn sprouts
		SproutSpawner.generateSprouts(gameMap);

		world.run();

	}

	/*

	The old map, just in case we need it again

				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");
	 */
}

