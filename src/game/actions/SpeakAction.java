package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.CanSpeak;

public class SpeakAction extends Action {

    private final CanSpeak speaker;

    public SpeakAction(CanSpeak speaker) {
        this.speaker = speaker;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return speaker.speak(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Mario talks with Toad";
    }
}