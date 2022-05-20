package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.BuyAction;
import game.actions.SpeakAction;
import game.actions.GiveItemAction;
import game.items.Bottle;
import game.items.Shovel;
import game.items.consumable.PowerStar;
import game.items.Wrench;
import game.items.consumable.SuperMushroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A slimy creature that can trade things
 */
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
        for (Item item : actor.getInventory()) { //checking if there is a wrench in the inventory
            if (item.toString().equals("Wrench")) {
                randBound = 3;
            }
        }
        for (Enum<?> capability : actor.capabilitiesList()) { //checking if the player is under the effects of the PowerStar
            if (capability == Status.INVINCIBLE) {
                randBound--;
            }
        }
        int dialogueNumber = rand.nextInt(randBound);
        return dialogue.get(dialogueNumber);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new SpeakAction(this));
        actions.add(new BuyAction(new PowerStar(), 600));
        actions.add(new BuyAction(new SuperMushroom(), 400));
        actions.add(new BuyAction(new Wrench(), 200));
        actions.add(new BuyAction(new Shovel(), 50));

        if (!otherActor.hasCapability(Status.HAS_A_BOTTLE)){
            List<Status> statusesToGive = new ArrayList<Status>();
            statusesToGive.add(Status.HAS_A_BOTTLE);
            actions.add(new GiveItemAction(statusesToGive," gets a free bottle from the smelly Toad", new Bottle()));
        }

        return actions;
    }

    /**
     * Fills Toad's dialogue attribute with the lines of dialogue he can potentially say.
     */
    private void setDialogue() {
        dialogue.add("The Princess is depending on you! You are our only hope.");
        dialogue.add("Being imprisoned in these walls can drive a fungus crazy :(");
        dialogue.add("You better get back to finding the Power Stars.");
        dialogue.add("You might need a wrench to smash Koopa's hard shells.");
    }
}
