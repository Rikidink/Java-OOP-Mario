package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * IF something can be bought
 */
public interface Buyable {

    /**
     * what to do when something is bought
     * @param actor the actor to do it on
     */
    void onBuy(Actor actor);
}
