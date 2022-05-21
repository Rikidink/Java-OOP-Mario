package game.ground;

/**
 * A wall that is very hard to jump on top of
 */
public class RuinWall extends HigherGround{
    /**
     * Constructor
     */
    public RuinWall() {
        super('%', 0.1, 40, "Ruin Wall");
    }

    /**
     * Method to determine if wall can block a thrown object
     *
     * @return true, blocks objects thrown
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
