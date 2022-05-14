package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.Dirt;
import game.ground.Spores;
import game.items.Coin;

public class DigAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        String output;
        if (Math.random() < 0.25) {
            map.locationOf(actor).setGround(new Spores());
            for (Exit exit : map.locationOf(actor).getExits()) {
                exit.getDestination().setGround(new Spores());
            }
                 output = (actor + " dug up a mysterious fungi, which released a cloud of toxic spores.");
        }
        else {
            map.at(map.locationOf(actor).x(), map.locationOf(actor).y()).addItem(new Coin(50, map.locationOf(actor).x(), map.locationOf(actor).y()));
            output = (actor + " dug up a $50 coin!");
            map.at(map.locationOf(actor).x(), map.locationOf(actor).y()).setGround(new Dirt());
        }
        return output;
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " digs for treasure.");
    }
}
