package game;

import edu.monash.fit2099.engine.actors.Actor;

public interface CanSpeak {

    /**
     *
     * @param actor The Actor who is being spoken to
     * @return      A String of dialogue
     */
    String speak(Actor actor);
}
