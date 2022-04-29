package game;

import edu.monash.fit2099.engine.positions.Ground;

import java.util.ArrayList;

abstract public class HigherGround extends Ground {

    protected double successRate;
    protected int fallDamage;
    protected String highGroundName;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HigherGround(char displayChar) {
        super(displayChar);
    }

    public double getSuccessRate(){
        return successRate;
    }

    public int getFallDamage(){
        return fallDamage;
    }

    public String getHighGroundName(){
        return highGroundName;
    }

}
