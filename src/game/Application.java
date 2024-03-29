package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;


import game.actors.*;
import game.actors.enemies.*;
import game.behaviours.GoToFountainBehaviour;
import game.ground.*;
import game.items.Bottle;
import game.items.Coin;
import game.items.NecromancyWeapon;
import game.items.consumable.PowerStar;
import game.items.consumable.SuperMushroom;
import game.items.fountain.HealthFountain;
import game.items.fountain.PowerFountain;
import game.reset.ResetManager;
import game.trees.*;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Lava(), new RuinWall());

		List<String> map = Arrays.asList(
				"..........................................##....................................",
				"............................................#...................................",
				"............................................#...................................",
				".............................................##.................................",
				"...............................................#................................",
				"................................................#...............................",
				"..................................................#.............................",
				".%%%%%%..........................................##.............................",
				"%______%%.......................................##..............................",
				"%______..................................#____####..............................",
				"%______%%...............................#_____###...............................",
				".%%%%%%.................................#______###..............................",
				".........................................#_____###..............................",
				".................................................##.............................",
				"...................................................#............................",
				"....................................................#...........................",
				".....................................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

		List<String> map2 = Arrays.asList(
				".............LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
				"....................LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.............LLLL",
				"........................LLLLLLLLLLLLLLLLLLLLL...............LLLLLL",
				"LL...........................................................LLLLL",
				"LLLLLLL....................................................LLLLLLL",
				"LLLLLLLLLLL............................................LLLLLLLLLLL",
				"LLLLLLLLLL..........................................LLLLLLLLLLLLLL",
				"LLLLLLLLLLL.............................................LLLLLLLLLL",
				"LLLLLLLLL...................................................LLLLLL",
				"LLLLLLL.................................................LLLLLLLLLL",
				"LLLLL.................................................LLLLLLLLLLLL",
				"LLLLLL....................................................LLLLLLLL",
				"LLLLLL....................................................LLLLLLLL",
				"LLLLLLL.................................................LLLLLLLLLL",
				"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL....................LLLLLLLLLLLLL",
				"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL............LLLLLLLLLLLLLLLLL");

		//create game-maps
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		GameMap gameMap2 = new GameMap(groundFactory,map2);
		world.addGameMap(gameMap2);

		//create player
		Actor mario = new Player("Player", 'm', 100);
		world.addPlayer(mario, gameMap.at(42, 10));

		//set up main world
		gameMap.at(42, 10).addItem(new PowerStar());
		gameMap.at(42, 10).addItem(new SuperMushroom());
		gameMap.at(42, 10).addItem(new Coin(5000, 42, 10));
		gameMap.at(44, 11).addActor(new Toad());
		gameMap.at(3, 9).addItem(new NecromancyWeapon());
		ResetManager.getInstance().setMainMap(gameMap);
		//add fountains
		gameMap.at(42, 8).addItem(new HealthFountain());
		gameMap.at(42, 7).addItem(new PowerFountain());

		//set up lava world
		gameMap2.at(1, 1).setGround(new Pipe(gameMap2));
		gameMap2.at(1, 1).addActor(new PiranhaPlant());
		gameMap2.at(32, 7).addActor(new PrincessPeach());
		gameMap2.at(31, 7).addActor(new Bowser(gameMap2));

		gameMap.at(7, 9).addActor(new GuardKoopa());
		gameMap.at(8, 9).addActor(new GuardKoopa());

		// spawn sprouts and soft grounds
		SproutSpawner.generateSprouts(gameMap);
		SoftGroundSpawner.generateSoftGround(gameMap);
		PipeSpawner.spawnPipes(gameMap,gameMap2);

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

