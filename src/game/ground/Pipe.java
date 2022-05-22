package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;

/**
 * Teleportation device
 */
public class Pipe extends HigherGround {

    /**
     * How long til PirhanaPlant spawns after the game starts
     */
    private int timeTilPiranhaPlantSpawn = 1;

    /**
     * Stores the lava map
     */
    private GameMap lavaMap;

    /**
     * Location of the current pipe
     */
    private Location pipeLocation;

    /**
     * Location of the pipe in the lava world
     */
    private Location lavaPipe;

    /**
     * Attribute of type Pipe which corresponds to the pipe in lava world
     */
    private Pipe pipeLava;

    /**
     * Location of the 'initial' pipe (i.e: pipe where mario warped from)
     */
    private Location initialPipe;

    /**
     * Constructor
     * @param lavaMap the lava map the pipe teleports to
     */
    public Pipe(GameMap lavaMap) {
        super('C', 1, 0, "Pipe");
        this.lavaMap = lavaMap;
        this.lavaPipe = lavaMap.at(1, 1);
        this.initialPipe = null;
    }

    /**
     * Method for adding the jump and teleport action to a pipe, also sets location and pipeLava attribute
     * @param actor     the Actor acting; i.e: the player
     * @param location  the current Location of the higher ground
     * @param direction the direction of the Ground from the Actor
     * @return          the list of actions for the Pipe
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        this.pipeLocation = location;
        this.pipeLava = (Pipe) lavaMap.at(1, 1).getGround();

        ActionList actions = new ActionList();
        if(!location.containsAnActor()) {
            actions.add(new JumpAction(location, direction, this));
        }
        else {
            actions.add(new TeleportAction(lavaMap, direction, this));

        }
        return actions;
    }

    /**
     * Getter for the location of the pipe
     * @return pipeLocation attribute
     */
    public Location getPipeLocation(){
        return pipeLocation;
    }

    /**
     * Getter for the location of the pipe in the lava world (1, 1)
     * @return lavaPipe attribute
     */
    public Location getLavaPipe(){
        return lavaPipe;
    }

    /**
     * Getter for the lava pipe as Pipe class
     * @return pipeLava attribute
     */
    public Pipe getPipeLava(){
        return pipeLava;
    }

    /**
     * Getter for the location of the pipe that mario came from
     * @return initialPipe attribute
     */
    public Location getInitialPipe(){
        return this.initialPipe;
    }

    /**
     * Setter for the initialPipe attribute
     * @param initialPipe a location to set the initialPipe attribute
     */
    public void setInitialPipe(Location initialPipe){
        this.initialPipe = initialPipe;
    }

    /**
     * Method that denotes if an actor can actor enter the pipe (false)
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        timeTilPiranhaPlantSpawn--;
        if (timeTilPiranhaPlantSpawn == 0) {
            if(!location.containsAnActor()) {
                location.addActor(new PiranhaPlant());
                timeTilPiranhaPlantSpawn--;
            }
        }
    }
}