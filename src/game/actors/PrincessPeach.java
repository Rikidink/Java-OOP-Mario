package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.EndGameAction;
import game.actions.SpeakAction;
import game.items.Key;

/**
 * A very peachy princess (no don't try to eat her)
 */
public class PrincessPeach extends Actor implements CanSpeak {
    String dialogue = "Thank you for saving me Mario! :)";

    /**
     * Constructor.
     *
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 9001);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public String speak(Actor actor) {
        return dialogue;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.CAN_END_GAME)) {
            actions.add(new EndGameAction(this));
        }
        return actions;
    }
}
