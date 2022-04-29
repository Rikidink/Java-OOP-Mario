package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;

public class Wall extends HigherGround {

	private final double successRate = 0.8;
	private final int fallDamage = 20;
	private final String highGroundName = "Wall";

	public Wall() {
		super('#');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		ActionList actions = new ActionList();

		actions.add(new JumpAction(location, actor, direction));
		return actions;
	}

	@Override
	public String getHighGroundName() {
		return highGroundName;
	}
}
