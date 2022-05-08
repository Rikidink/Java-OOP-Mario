package game.actors;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    CAN_RESET, // does the player have the ability to reset the game

    //power star effects
    INVINCIBLE, //if a player is (at least temporarily) invincible
    WALK_TO_HIGHER_GROUND, //if an actor can walk (not jump to higher ground), this destroys the higher ground and drops a $5 coin
    INSTA_KILLER, //the actor insta kills anything it successfully attacks. INVINCIBLE overrides this

    HAS_EATEN_POWER_STAR_THIS_TURN, // if the power star has been eaten this turn (used for resting count down)
    IS_DORMANT, //if an actor is dormant - related to being attacked
    CAN_BE_DORMANT, //if an actor can become IS_DORMANT
    FOLLOWING, //indicates that the enemy is following the player
    CANNOT_ENTER_FLOOR, //for enemies, so that they cannot walk on the floor
    FERTILE_GROUND, // given to grounds if they can support trees
    HOSTILE_TO_PLAYER, //if an actor is hostile to the player
}
