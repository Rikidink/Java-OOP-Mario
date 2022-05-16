package game.actions.fountain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.fountain.Fountain;

public class DrinkPowerWaterAction extends DrinkAction{

    public DrinkPowerWaterAction(String type, Fountain fountain, boolean directlyRelated) {
        super(type, fountain, directlyRelated);
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        setHasBeenUsed(true);

        actor.addCapability(Status.NEEDS_TO_BOOST_POWER_15);


        if (directlyRelated == true){
            fountain.reduceRemainingWater();
        }
        return actor +" embraces the grind. The powerfull water increases " + actor + "'s base attack damage by 15 points";
    }


}
