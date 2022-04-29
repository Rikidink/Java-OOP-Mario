package game.trees;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.items.Coin;

public class Sapling extends Tree {

    private final double successRate = 0.8;
    private final int fallDamage = 20;
    private final String highGroundName = "Sapling";

    public Sapling(int x, int y){
        super('t', x, y);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCount += 1;

        if(turnCount == 10){
            location.setGround(new Mature(mario, location.x(), location.y()));
        }
        else {
            if(Math.random() <= 0.10){
                location.addItem(new Coin(20,location.x(), location.y()));
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
