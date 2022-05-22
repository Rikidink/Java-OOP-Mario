package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.CanSpeak;

/**
 * Action for ending the game
 */
public class EndGameAction extends SpeakAction {

    /**
     * Constructor
     *
     * @param speaker The CanSpeak who will be doing the speaking.
     */
    public EndGameAction(CanSpeak speaker) {
        super(speaker);
    }

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        map.removeActor(actor);
        return "Congratulations. You have freed Princess Peach. The game has now ended :)";
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " frees Princess Peach and ends the game");
    }
}
