package game.ground;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Wall; class that extends HigherGround since player can jump to a wall
 */
public class Wall extends HigherGround {

	/**
	 * Constructor to create a wall
	 *
	 */
	public Wall() {
		super('#', 0.8, 20, "Wall");
	}

	/**
	 * Method to determine if wall can block a thrown object
	 *
	 * @return true, blocks objects thrown
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

}
