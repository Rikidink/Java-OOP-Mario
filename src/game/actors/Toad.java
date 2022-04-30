package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.actions.SpeakAction;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

import java.util.ArrayList;
import java.util.Random;

public class Toad extends Actor implements CanSpeak {

    private final ArrayList<String> dialogue = new ArrayList<>();

    /**
     * Random number generator
     *
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

    /**
     * Select and return an action to perform on the current turn.
     *
     * @see Actor#playTurn(ActionList actions, Action lastAction, GameMap map, Display display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Randomly chooses a line for Toad to say to the Player.
     * Checks if the Player is holding a Wrench or under the effects of a PowerStar.
     * If either of these are true it omits the related dialogue options, so they cannot be chosen.
     *
     * @param actor The actor that Toad is speaking to i.e. the Player
     * @return      The randomly chosen dialogue option that Toad will speak
     */
    public String speak(Actor actor) {
        int randBound = 4;
        int hasWrench = 5;
        int hasPowerStar = 5;

        for (Item item : actor.getInventory()) { //checking if there is a wrench in the inventory
            if (item.toString().equals("Wrench")) {
                hasWrench = 2;
            }
        }

        if (actor.hasCapability(Status.HAS_EATEN_POWER_STAR)) {
                hasPowerStar = 3;
            }

        int dialogueNumber = rand.nextInt(randBound);

        while (dialogueNumber == hasPowerStar || dialogueNumber == hasWrench) {
            dialogueNumber = rand.nextInt(randBound);
        }
        return dialogue.get(dialogueNumber);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new SpeakAction(this));
        actions.add(new BuyAction(new PowerStar(), 600));
        actions.add(new BuyAction(new SuperMushroom(), 400));
        actions.add(new BuyAction(new Wrench(), 200));
        return actions;
    }

    /**
     * Fills Toad's dialogue attribute with the lines of dialogue he can potentially say.
     *
     */
    private void setDialogue() {
        dialogue.add("The Princess is depending on you! You are our only hope.");
        dialogue.add("Being imprisoned in these walls can drive a fungus crazy :(");
        dialogue.add("You might need a wrench to smash Koopa's hard shells.");
        dialogue.add("You better get back to finding the Power Stars.");
    }
}
