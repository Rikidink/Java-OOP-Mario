package game.actions.fountain;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.fountain.Fountain;

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

    /**
     * If the action has been used - an thus needs to be deleted
     */
    private boolean hasBeenUsed = false;

    public void setHasBeenUsed(boolean value){
        hasBeenUsed = value;
    }

    public boolean getHasBeenUsed(){
        return hasBeenUsed;
    }


    public DrinkAction(String type, Fountain fountain, boolean directlyRelated){
        this.type = type;
        this.fountain = fountain;
        this.directlyRelated = directlyRelated;

    }


    abstract public String execute(Actor actor, GameMap map);

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
