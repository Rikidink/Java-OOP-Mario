package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.Wallet;

import java.util.ArrayList;
import java.util.List;

/**
 * like a trade action, but it gives mario a status not an item. This
 * status is intended to unlock an item
 */
public class StatusGiveAction extends Action  {

    private List<Status> statusesToGive = new ArrayList<Status>();

    private String description;

    public StatusGiveAction(List<Status> statusesToGive, String description) {
        this.statusesToGive = statusesToGive;
        this.description = description;

    }



    @Override
    public String execute(Actor actor, GameMap map) {
        //add all of the statuses
        for (Enum<?> status : statusesToGive) {
            actor.addCapability(status);

        }
        return menuDescription(actor);
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + description;
    }
}




