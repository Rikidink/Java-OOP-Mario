package game.actions.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountain.Fountain;
import game.items.consumable.StorableFood;

public abstract class DrinkAction extends Action {

    /**
     * If the drink action is directly related to the fountain (eg stored in a bottle vs directly taking from fountain
     */
    protected boolean directlyRelated;

    /**
     * what kinda thing are you drinking?
     */
    protected String type;

    /**
     * The Fountain where the water comes from
     */
    protected Fountain fountain;


    public DrinkAction(String type, Fountain fountain, boolean directlyRelated){
        this.type = type;
        this.fountain = fountain;
        this.directlyRelated = directlyRelated;

    }


    abstract public String execute(Actor actor, GameMap map);

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks the water from the " + type + " " +  fountain.getWaterInfo();
    }
}
