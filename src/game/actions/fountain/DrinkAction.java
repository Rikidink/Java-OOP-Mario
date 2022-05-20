package game.actions.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.ModifiableIntrinsicWeaponActor;
import game.items.fountain.Fountain;

public class DrinkAction extends Action {

    /**
     * what kinda thing are you drinking?
     */
    protected String type;

    /**
     * The Fountain where the water comes from
     */
    protected Fountain fountain;

    private boolean fromBottleFlag;

    public DrinkAction(String type, Fountain fountain, boolean fromBottleFlag){
        this.type = type;
        this.fountain = fountain;
        this.fromBottleFlag = fromBottleFlag;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (fromBottleFlag) {
            return (fountain.effects((ModifiableIntrinsicWeaponActor) actor));
        }
        else if (fountain.canDrinkFrom()) {
            fountain.reduceRemainingWater(5);
            return (fountain.effects((ModifiableIntrinsicWeaponActor) actor));
        }
        return "The fountain does not have enough water to drink from yet.";
    }

    @Override
    public String menuDescription(Actor actor) {
        //for bottles
        if (fountain == null) {
            return actor + " drinks the " + type + " water from the bottle";
        }
        //for sipping
        return actor + " drinks the water from the " + type + " " +  fountain.getWaterInfo();
    }
}
