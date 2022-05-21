package game.actions.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.fountain.Fountain;

public class FillBottleAction extends Action {

    private final DrinkAction drinkAction;
    private final Fountain fountain;

    public FillBottleAction(DrinkAction drinkAction, Fountain fountain) {
        this.drinkAction = drinkAction;
        this.fountain = fountain;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (fountain.canFillBottle()){
            Bottle.getInstance().addConsumableToBottle(drinkAction);
            fountain.reduceRemainingWater(5);
            return "The bottle is filled with " + drinkAction.type;
        }
        else {
            return "The fountain does not have enough water to refill your bottle yet.";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills bottle from " + fountain;
    }
}
