package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Pipe;

/**
 * Action for teleportation
 */
public class TeleportAction extends Action {

    /**
     * Attribute for the lava map
     */
    private GameMap lavaMap;

    /**
     * Direction of the pipe mario jumps to
     */
    private String direction;

    /**
     * Pipe that mario teleports through
     */
    private Pipe targetPipe;

    /**
     * Constructor
     * @param lavaMap lavaworld to teleport to
     * @param direction direction of the pipe mario jumps to
     * @param pipe pipe used to teleport
     */
    public TeleportAction(GameMap lavaMap, String direction, Pipe pipe){
        this.direction = direction;
        this.lavaMap = lavaMap;
        this.targetPipe = pipe;
    }

    /**
     * Method for actually teleporting mario
     * Checks if the pipe's initial pipe is null (always null for tree pipes) and does the
     * appropriate teleport
     * @param actor The actor performing the action.
     * @param treeMap
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap treeMap) {
            if(targetPipe.getInitialPipe() == null){
                lavaMap.removeActor(targetPipe.getLavaPipe().getActor());

                // Move actor to lavaPipe
                MoveActorAction move = new MoveActorAction(targetPipe.getLavaPipe(), direction);
                move.execute(actor,treeMap);

                // Sets the initialPipe attribute at the lavaPipe to the one mario came from
                targetPipe.getPipeLava().setInitialPipe(targetPipe.getPipeLocation());
                return "Teleported to the lava world!";
            }
            else {

                // Uses initialPipe attribute from the lavaPipe
                MoveActorAction move = new MoveActorAction(targetPipe.getInitialPipe(),direction);
                move.execute(actor, lavaMap);

                return "Teleported back to the tree world!";
            }
    }


    /**
     * String output when mario is on the pipe
     * @param actor The actor performing the action.
     * @return "Teleport to lava world!" output
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Teleport to lava world!";

    }

}