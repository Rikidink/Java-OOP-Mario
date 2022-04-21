package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree());

		List<String> map = Arrays.asList(
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

		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		Actor mario = new Player("Player", 'm', 100);
		world.addPlayer(mario, gameMap.at(42, 10));

		gameMap.at(44, 11).addActor(new Toad());

		// FIXME: the Goomba should be generated from the Tree
		gameMap.at(35, 10).addActor(new Goomba(mario));
		gameMap.at(34, 10).addActor(new Goomba(mario));
		gameMap.at(33, 10).addActor(new Goomba(mario));
		gameMap.at(32, 10).addActor(new Goomba(mario));
		gameMap.at(31, 10).addActor(new Goomba(mario));
		gameMap.at(35, 9).addActor(new Goomba(mario));
		gameMap.at(34, 9).addActor(new Goomba(mario));
		gameMap.at(33, 9).addActor(new Goomba(mario));
		gameMap.at(32, 9).addActor(new Goomba(mario));
		gameMap.at(31, 9).addActor(new Goomba(mario));


		world.run();

	}
}