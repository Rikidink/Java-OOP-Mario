package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;

import java.util.List;

/**
 * The power star item, greatest of all items. He who consumes this shall gain power unmatched
 *
 */
public class PowerStar extends Item {


	/***
	 * Constructor
	 */
	public PowerStar() {
		super("Power Star", '*', true);

		super.addAction(new ConsumePowerStarAction(this)); //add can be consumed as an allowable action
		//get if to only work if off the ground - NOT NEEDED

	}
}
