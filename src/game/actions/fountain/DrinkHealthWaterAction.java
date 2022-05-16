package game.actions.fountain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.fountain.Fountain;

public class DrinkHealthWaterAction extends DrinkAction{

    public DrinkHealthWaterAction(String type, Fountain fountain, boolean directlyRelated) {
        super(type, fountain, directlyRelated);
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        setHasBeenUsed(true);

        actor.heal(50);


        if (directlyRelated == true){
            fountain.reduceRemainingWater();
        }
        return "The healthy water heals " + actor + " by 50 points";
    }


}
