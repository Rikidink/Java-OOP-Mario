package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
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
     * Pipe that mario teleports through
     */
    private Pipe targetPipe;

    /**
     * Constructor
     * @param lavaMap   lavaworld to teleport to
     * @param pipe      pipe used to teleport
     */
    public TeleportAction(GameMap lavaMap, Pipe pipe){
        this.lavaMap = lavaMap;
        this.targetPipe = pipe;
    }

    /**
     * Method for actually teleporting mario
     * Checks if the pipe's initial pipe is null (always null for tree pipes) and does the
     * appropriate teleport
     * @param actor     The actor performing the action.
     * @param treeMap   The map of the original world you spawned in
     * @return          Output to console once this Action is complete
     */
    @Override
    public String execute(Actor actor, GameMap treeMap) {
            if(targetPipe.getInitialPipe() == null){
                if(targetPipe.getLavaPipe().getActor() != null) {
                    lavaMap.removeActor(targetPipe.getLavaPipe().getActor());
                }
                // Move actor to lavaPipe
                treeMap.moveActor(actor,targetPipe.getLavaPipe());

                // Sets the initialPipe attribute at the lavaPipe to the one mario came from
                targetPipe.getPipeLava().setInitialPipe(targetPipe.getPipeLocation());
                return "Teleported to the lava world!";
            }
            else {

                // Uses initialPipe attribute from the lavaPipe
                lavaMap.moveActor(actor, targetPipe.getInitialPipe());

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
        if(!lavaMap.contains(actor)){
            return "Teleport to lava world!";
        }
        else {
            return "Teleport back to tree world!";
        }

    }
}