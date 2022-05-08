package game.items.consumable;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action for consuming items
 */
public class ConsumeAction extends Action {

    private Consumable consumable;

    /**
     * what is printed to the menu for the consume action
     */
    private String menuDescriptionText;

    /**
     * Constructor
     * @param consumable
     */
    public ConsumeAction(Consumable consumable, String menuDescriptionText){
        this.consumable = consumable;

        setMenuDescriptionText(menuDescriptionText);

    }


    @Override
    public String execute(Actor actor, GameMap map) {
        consumable.addStatuses(actor);
        consumable.otherThingsToDoWhenConsumed(actor, map);


        return menuDescription(actor);
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + menuDescriptionText;
    }

    public void setMenuDescriptionText(String menuDescriptionText){
        this.menuDescriptionText = menuDescriptionText;
    }

}
