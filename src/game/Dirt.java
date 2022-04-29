package game;

import edu.monash.fit2099.engine.positions.Ground;


/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground{

	/**
	 * Constructor for dirt, has capability of fertile ground
	 */
	public Dirt() {
		super('.');
		this.addCapability(Status.FERTILE_GROUND);
	}

}
