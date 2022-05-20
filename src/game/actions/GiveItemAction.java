package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * like a trade action, but it gives mario a status not an item. This
 * status is intended to unlock an item
 */
public class GiveItemAction extends Action  {

    private List<Status> statusesToGive = new ArrayList<Status>();
    private String description;
    Item item;

    public GiveItemAction(List<Status> statusesToGive, String description, Item item) {
        this.statusesToGive = statusesToGive;
        this.description = description;
        this.item = item;
    }



    @Override
    public String execute(Actor actor, GameMap map) {
        //add all of the statuses
        for (Enum<?> status : statusesToGive) {
            actor.addCapability(status);

        }
        actor.addItemToInventory(item);
        return menuDescription(actor);
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + description;
    }
}




