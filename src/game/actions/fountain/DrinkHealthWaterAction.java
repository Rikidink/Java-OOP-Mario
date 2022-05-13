package game.actions.fountain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountain.Fountain;

public class DrinkHealthWaterAction extends DrinkAction{

    public DrinkHealthWaterAction(String type, Fountain fountain, boolean directlyRelated) {
        super(type, fountain, directlyRelated);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(50);

        if (directlyRelated == true){
            super.fountain.reduceRemainingWater();
            System.out.println("reducing watewr");
        }
        return "The healthy water heals " + actor + " by 50 points";
    }
}
