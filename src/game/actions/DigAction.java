package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.Dirt;
import game.items.Coin;

public class DigAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        if (Math.random() < 0.25) {
            System.out.println("SPORES");
        }
        else {
            map.at(map.locationOf(actor).x(), map.locationOf(actor).y()).addItem(new Coin(50, map.locationOf(actor).x(), map.locationOf(actor).y()));
            System.out.println(actor + " dug up a $50 coin!");
        }
        map.at(map.locationOf(actor).x(), map.locationOf(actor).y()).setGround(new Dirt());
        return "dug is done";
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " digs for treasure.");
    }
}
