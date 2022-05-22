package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.Status;
import java.util.List;

/**
 * Action which directs Enemies to go to a Fountain (and then later drink from the fountain)
 */
public class GoToFountainAction extends Action {

    /**
     * x-axis position of the closest fountain
     */
    private int x;

    /**
     * y-axis position of the closest fountain
     */
    private int y;

    /**
     * Perform the Action
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        findClosestFountainCords(map, actor);

        //if the actor is already at the fountain 50% chance to remove the status
        if (map.locationOf(actor).x() == x && map.locationOf(actor).y() == y) {
            actor.removeCapability(Status.WANTS_TO_GO_TO_A_FOUNTAIN);
            return actor + " arrives at a fountain";

        } else {
            //if the actor is not already at the fountain


            Location destination;

            for (Exit exit : map.locationOf(actor).getExits()) {
                destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newXDistance = Math.abs(destination.x() - x);
                    int newYDistance = Math.abs(destination.y() - y);

                    int oldXDistance = Math.abs(map.locationOf(actor).x() - x);
                    int oldYDistance = Math.abs(map.locationOf(actor).y() - y);


                    if (Math.pow(newXDistance,2) + Math.pow(newYDistance,2) < Math.pow(oldXDistance,2) + Math.pow(oldYDistance,2)) {
                        // if the distance is shorter. using square things because I am cool

                        map.moveActor(actor, map.at(destination.x(),destination.y()));
                        return menuDescription(actor);
                    }

                }

            }
            actor.removeCapability(Status.WANTS_TO_GO_TO_A_FOUNTAIN); //so after getting stuck it will wander again
            return actor + " tries to wander to a fountain... but is stuck";

        }
    }

    /**
     * Returns a descriptive String
     *
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " wanders towards a fountain";
    }

    /**
     * Finds the closest fountain to an actor
     *
     * @param map   the map to find the fountain on
     * @param actor the actor who is looking for a fountain
     * @return      whether a fountain was found
     */
    public boolean findClosestFountainCords(GameMap map, Actor actor) {

        //get the location of the actor
        Location actorLocation = map.locationOf(actor);
        int actorX = actorLocation.x();
        int actorY = actorLocation.y();

        //the min difference from fountain to actor
        int minX = 100;
        int minY = 100;

        //gets all of the fountain locations on the map

        //return a list of fountain

        NumberRange mapY = map.getYRange();
        NumberRange mapX = map.getXRange();

        //scan the whole map for fountains
        for (int y : mapY) {
            for (int x : mapX) {
                List<Item> items = map.at(x, y).getItems();
                for (Item item : items) {
                    if (item.hasCapability(Status.IS_A_FOUNTAIN)) { // fountain found - now is it closest?
                        if (Math.abs(actorX - x) + Math.abs(actorY - y) < Math.abs(actorX - minX) + Math.abs(actorY - minY)) {
                            minX = x;
                            minY = y;
                        }
                    }
                }
            }
        }

        this.x = minX;
        this.y = minY;

        //ie if no fountains found
        return this.x != 100;
    }
}