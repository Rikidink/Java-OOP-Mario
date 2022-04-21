package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.Random;

public class Toad extends Actor implements CanSpeak {

    private final ArrayList<String> dialogue = new ArrayList<>();

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     */
    public Toad() {
        super("Toad", 'O', 50);
        setDialogue();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public String speak() {
        int dialogueNumber = rand.nextInt(4);
        return dialogue.get(dialogueNumber);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new SpeakAction(this));
        return actions;
    }

    private void setDialogue() {
        dialogue.add("You might need a wrench to smash Koopa's hard shells.");
        dialogue.add("You better get back to finding the Power Stars.");
        dialogue.add("The Princess is depending on you! You are our only hope.");
        dialogue.add("Being imprisoned in these walls can drive a fungus crazy :(");
    }
}
