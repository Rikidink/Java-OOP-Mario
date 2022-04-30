package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An Interface for allowing an Actor to be able to Speak
 */
public interface CanSpeak {

    /**
     *A method that determines what the Actor implementing CanSpeak says when it speaks
     *
     * @param actor The Actor who is being spoken to
     * @return      A String of dialogue
     */
    String speak(Actor actor);
}
