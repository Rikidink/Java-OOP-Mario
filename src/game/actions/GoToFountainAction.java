package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.Status;
import game.trees.Sprout;
import java.util.List;
import java.util.Random;

public class GoToFountainAction extends Action {

    /**
     * y of the closest fountain
     */
    private int x;

    /**
     * y of the closest fountain
     */
    private int y;

    public GoToFountainAction(Actor actor, GameMap map){
        System.out.println("initialising go to fountain!!");
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println("going to a fountain");

        findClosestFountainCords(map, actor);

        //if the actor is already at the fountain
        if (map.locationOf(actor).x() == x && map.locationOf(actor).y() == y) {
            actor.removeCapability(Status.WANTS_TO_GO_TO_A_FOUNTAIN);
            return actor + " has arrived at a fountain!";

        } else {
            //if the actor is not already at the fountain
            Location destination;

            for (Exit exit : map.locationOf(actor).getExits()) {
                destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newXDistance = Math.abs(x - destination.x());
                    int newYDistance = Math.abs(y - destination.y());

                    int oldXDistance = Math.abs(map.locationOf(actor).x());
                    int oldYDistance = Math.abs(map.locationOf(actor).y());

                    if (newXDistance + newYDistance < oldXDistance + oldYDistance) { // if the distance is shorter
                        Location newLocation = new Location(map, newXDistance, newYDistance);
                        map.moveActor(actor, newLocation);
                        return menuDescription(actor);
                    }

                }

            }
            return actor + " tries to wander to a fountain... but is stuck";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " wanders towards a fountain";
    }

    /**
     * finds the closest fountain to an actor and returns the coordinants
     * @param map the map to find the fountain on
     * @param actor the actor of whome to find the closest fountain for
     */
    private void findClosestFountainCords(GameMap map, Actor actor){

        //get the location of the actor
        Location actorLocation = map.locationOf(actor);
        int actorX = actorLocation.x();
        int actorY = actorLocation.y();

        //the min difference from fountain to actor
        int minX = 100;
        int minY = 100;

        //gets all of the fountain locations on the map

        //return a list of fountain
        Random rand = new Random();
        // amount of sprouts that should be spawned on the map
        int sproutAmount = 30;
        NumberRange mapY = map.getYRange();
        NumberRange mapX = map.getXRange();

        //scan the whole map for fountains

        for (int y: mapY){
            for (int x: mapX){
                List<Item> items = map.at(x,y).getItems();
                for (Item item: items){
                    if (item.hasCapability(Status.IS_A_FOUNTAIN)){ // fountain found - now is it closest?
                        if ( Math.abs(actorX - x)  + Math.abs(actorY - y) < Math.abs(actorX - minX) + Math.abs(actorY - minY)){
                            minX = x;
                            minY = y;

                        }
                    }
                }
            }

        }

        this.x = minX;
        this.y = minY;
    }
}