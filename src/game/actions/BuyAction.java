package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class BuyAction extends Action {

    private Actor buyer;
    //RF: change these to the actual item types once they have been added
    private String item;
    private Integer price;

    public BuyAction(Actor buyer, String item, Integer price) {
        this.buyer = buyer;
        this.item = item;
        this.price = price;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return (actor + " buys " + item);
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " buys " + item + "($" + price + ")");
    }
}
