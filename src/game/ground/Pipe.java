package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.JumpAction;
import game.actions.TeleportAction;

public class Pipe extends HigherGround {

    private GameMap treeMap, lavaMap;
    private Location pipeLocation;
    private Location lavaPipe;
    private Pipe initialPipe;
    private int x;
    private int y;

    /**
     * Constructor.
     */
    public Pipe(GameMap treeMap, GameMap lavaMap) {
        super('C', 1, 0, "Pipe");
        this.treeMap = treeMap;
        this.lavaMap = lavaMap;
        this.lavaPipe = lavaMap.at(1, 1);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        this.pipeLocation = location;
        ActionList actions = new ActionList();
        if(!location.containsAnActor()) {
            actions.add(new JumpAction(location, direction, this));
        }
        else {
            actions.add(new TeleportAction(location, lavaMap, direction, this));

        }
        return actions;
    }

    public Location getPipeLocation(){
        return pipeLocation;
    }

    public Location getLavaPipe(){
        return lavaPipe;
    }

    public Pipe getInitialPipe(){
        return initialPipe;
    }

    public void setInitialPipe(Location initialPipe){

    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}