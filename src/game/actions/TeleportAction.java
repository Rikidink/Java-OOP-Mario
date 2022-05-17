package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Pipe;

public class TeleportAction extends Action {

    private Location location;
    private Location initialPipe;
    private GameMap lavaMap;
    private String direction;
    private Pipe targetPipe;

    public TeleportAction(Location location, GameMap lavaMap, String direction, Pipe pipe){
        this.location = location;
        this.direction = direction;
        this.lavaMap = lavaMap;
        this.initialPipe = null;
        this.targetPipe = pipe;
    }

    @Override
    public String execute(Actor actor, GameMap treeMap) {
        if(treeMap.contains(actor)){
            if(location.containsAnActor()){
                MoveActorAction move = new MoveActorAction(targetPipe.getLavaPipe(), direction);
                move.execute(actor,treeMap);
                targetPipe.setInitialPipe(targetPipe.getPipeLocation());
                return "Teleported!";
            }

        }
        else {
            return "test";
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Teleport to lava world";

    }

}