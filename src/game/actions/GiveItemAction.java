package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * like a trade action, except a gift :). Also can give Status effects related to the Item given
 */
public class GiveItemAction extends Action  {

    /**
     * The statuses to be added to the capability list of the Actor receiving the Item
     */
    private List<Status> statusesToGive = new ArrayList<Status>();

    /**
     * The description to be shown in the menu for this Action
     */
    private String description;

    /**
     * The Item that is given by this Action
     */
    Item item;

    /**
     * Constructor
     *
     * @param statusesToGive The statuses to be added to the capability list of the Actor receiving the Item
     * @param description    The description to be shown in the menu for this Action
     * @param item           The Item that is given by this Action
     */
    public GiveItemAction(List<Status> statusesToGive, String description, Item item) {
        this.statusesToGive = statusesToGive;
        this.description = description;
        this.item = item;
    }

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //add all of the statuses
        for (Enum<?> status : statusesToGive) {
            actor.addCapability(status);

        }
        actor.addItemToInventory(item);
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + description;
    }
}




