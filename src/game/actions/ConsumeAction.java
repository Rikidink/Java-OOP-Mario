package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumable.Consumable;

/**
 * Action for consuming items
 */
public class ConsumeAction extends Action {

    /**
     * The Consumable that will be consumed by this action
     */
    private Consumable consumable;

    /**
     * The text for the menu description for this Action
     */
    private String menuDescriptionText;

    /**
     * Constructor
     * @param consumable          The Consumable that will be consumed by this action
     * @param menuDescriptionText The text for the menu description for this Action
     */
    public ConsumeAction(Consumable consumable, String menuDescriptionText){
        this.consumable = consumable;
        setMenuDescriptionText(menuDescriptionText);
    }

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumable.addStatuses(actor);
        consumable.otherThingsToDoWhenConsumed(actor, map);


        return menuDescription(actor);
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + menuDescriptionText;
    }

    /**
     * Sets the text for the menu description
     *
     * @param menuDescriptionText The text for the menu description for this Action
     */
    public void setMenuDescriptionText(String menuDescriptionText){
        this.menuDescriptionText = menuDescriptionText;
    }
}
