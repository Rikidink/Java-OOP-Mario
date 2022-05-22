package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.EndGameAction;

/**
 * A very peachy princess (no don't try to eat her)
 */
public class PrincessPeach extends Actor implements CanSpeak {

    /**
     * The dialogue line that Princess Peach can Speak
     */
    String dialogue = "Thank you for saving me Mario! :)";

    /**
     * Constructor.
     *
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 9001);
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     *A method that determines what the Actor implementing CanSpeak says when it speaks
     *
     * @param actor The Actor who is being spoken to
     * @return      A String of dialogue
     * @see CanSpeak#speak(Actor actor)
     */
    @Override
    public String speak(Actor actor) {
        return dialogue;
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.CAN_END_GAME)) {
            actions.add(new EndGameAction(this));
        }
        return actions;
    }
}
