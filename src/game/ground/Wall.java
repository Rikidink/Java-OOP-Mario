package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import game.ground.HigherGround;

/**
 * Wall; class that extends HigherGround since player can jump to a wall
 */
public class Wall extends HigherGround {

	/**
	 * Constructor to create a wall
	 */
	public Wall() {
		super('#', 0.8, 20, "Wall");
	}

	/**
	 * Method that determines if an actor can enter a wall
	 * @param actor the Actor to check
	 * @return false, can't enter a wall
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Method to determine if wall can block a thrown object
	 * @return true, blocks objects thrown
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * Method to return the success rate of the jump
	 * @return success rate
	 */
	@Override
	public double getSuccessRate() {
		return successRate;
	}

	/**
	 * Method to return fall damage amount if player jump fails
	 * @return fall damage amount
	 */
	@Override
	public int getFallDamage() {
		return fallDamage;
	}

	/**
	 * Method to return the name of the wall (Wall)
	 * @return Wall as a string
	 */
	@Override
	public String getHighGroundName() {
		return highGroundName;
	}


}