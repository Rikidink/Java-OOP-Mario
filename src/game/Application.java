package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

import game.actors.Goomba;
import game.actors.Koopa;
import game.actors.Player;
import game.actors.Toad;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.trees.Sprout;
import game.trees.Tree;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		// replaced tree constructor with sprout
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

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
		//gameMap.at(43, 8).addActor(new Koopa(mario));
		//gameMap.at(43, 7).addActor(new Koopa(mario));

		gameMap.at(42, 10).addItem(new PowerStar());
		gameMap.at(42, 10).addItem(new PowerStar());


		world.run();

	}
}

