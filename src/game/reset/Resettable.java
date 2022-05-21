package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * If something can be reset when the great reset happens
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     * HINT: play around with capability, the actual implementation happens in the tick or playTurn method.
     *
     */
    void resetInstance(GameMap map);

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     *
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
