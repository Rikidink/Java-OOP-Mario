package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.CanSpeak;

/**
 * An Action for speaking to another Actor that CanSpeak
 */
public class SpeakAction extends Action {

    private final CanSpeak speaker;

    /**
     * Constructor
     *
     * @param speaker   The CanSpeak who will be doing the speaking.
     */
    public SpeakAction(CanSpeak speaker) {
        this.speaker = speaker;
    }

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return speaker.speak(actor);
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Mario talks with " + speaker.toString();
    }
}
