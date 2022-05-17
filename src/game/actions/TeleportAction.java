package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Pipe;

public class TeleportAction extends Action {

    private GameMap lavaMap;
    private String direction;
    private Pipe targetPipe;

    public TeleportAction(GameMap lavaMap, String direction, Pipe pipe){
        this.direction = direction;
        this.lavaMap = lavaMap;
        this.targetPipe = pipe;
    }

    @Override
    public String execute(Actor actor, GameMap treeMap) {
            if(targetPipe.getInitialPipe() == null){
                lavaMap.removeActor(targetPipe.getLavaPipe().getActor());
                MoveActorAction move = new MoveActorAction(targetPipe.getLavaPipe(), direction);
                move.execute(actor,treeMap);
                targetPipe.getPipeLava().setInitialPipe(targetPipe.getPipeLocation());
                return "Teleported to the lava world!";
            }
            else {
                MoveActorAction move = new MoveActorAction(targetPipe.getInitialPipe(),direction);
                move.execute(actor, lavaMap);

                return "Teleported back to the tree world!";
            }
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Teleport to lava world";

    }

}