package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.Status;
import game.actors.Koopa;

import java.util.ArrayList;
import java.util.Random;

public class Mature extends Tree {

    private final double successRate = 0.7;
    private final int fallDamage = 30;
    private final String highGroundName = "Mature";


    public Mature(Actor mario, int x, int y){
        super('T', x, y);
        this.mario = mario;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        if(Math.random() <= 0.20){
            location.setGround(new Dirt());
        }
        else if(turnCount % 5 == 0){
            Random rand = new Random();
            ArrayList<Exit> exits = new ArrayList<>();

            for(int i = 0; i < location.getExits().size(); i++){
                if(location.getExits().get(i).getDestination().getGround().hasCapability(Status.FERTILE_GROUND)){
                    exits.add(location.getExits().get(i));
                }
            }
            if(exits.size() > 0){
                int randExitNum = rand.nextInt(exits.size());
                exits.get(randExitNum).getDestination().setGround(new Sprout(mario, location.x(), location.y()));
            }

        }
        else {
            if(Math.random() <= 0.15){
                if(!location.containsAnActor()){
                   location.addActor(new Koopa(mario));
                }
            }
        }


    }

    @Override
    public void resetInstance(GameMap map) {
        if (Math.random() <= 0.5) {
            map.at(x, y).setGround(new Dirt());
        }
    }

    @Override
    public void registerInstance() {
        super.registerInstance();
    }

    @Override
    public double getSuccessRate() {
        return successRate;
    }

    @Override
    public int getFallDamage() {
        return fallDamage;
    }

    @Override
    public String getHighGroundName() {
        return highGroundName;
    }
}
