package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    CAN_RESET, // if something can be reset
    HAS_EATEN_SUPER_MUSHROOM, //if an actor needs to have the effects of having eaten a mushroom
    HAS_EATEN_POWER_STAR, //if a player has eaten a power star
    IS_DORMANT, //if an actor is dormant - related to being attacked
    CAN_BE_DORMANT //if an actor can become IS_DORMANT
}
