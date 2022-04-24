package game.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.actors.Koopa;

import java.util.ArrayList;
import java.util.Random;

public class Mature extends Tree {

    public Mature(Actor mario){
        super('T');
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
                if(location.getExits().get(i).getDestination().getGround() instanceof Dirt){
                    exits.add(location.getExits().get(i));
                }
            }
            if(exits.size() > 0){
                int randExitNum = rand.nextInt(exits.size());
                exits.get(randExitNum).getDestination().setGround(new Sprout(mario));
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
}
