package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.PiranhaPlant;
import game.ground.JumpAction;
import game.actions.TeleportAction;

public class Pipe extends HigherGround {

    private int timeTilPiranhaPlantSpawn = 1;
    private GameMap lavaMap;
    private Location pipeLocation;
    private Location lavaPipe;
    private Pipe pipeLava;
    private Location initialPipe;


    /**
     * Constructor.
     */
    public Pipe(GameMap lavaMap) {
        super('C', 1, 0, "Pipe");
        this.lavaMap = lavaMap;
        this.lavaPipe = lavaMap.at(1, 1);
        this.initialPipe = null;
    }

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

    public Location getPipeLocation(){
        return pipeLocation;
    }

    public Location getLavaPipe(){
        return lavaPipe;
    }

    public Pipe getPipeLava(){
        return pipeLava;
    }
    public Location getInitialPipe(){
        return this.initialPipe;
    }

    public void setInitialPipe(Location initialPipe){
        this.initialPipe = initialPipe;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

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