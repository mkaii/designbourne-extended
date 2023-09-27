package game.capabilities;

/**
 * Use this enum to represent abilities.
 * Example #1: if the player is capable jumping over walls, you can attach Ability.WALL_JUMP to the player class
 */
public enum Ability {
    /**
     * The ability to unlock doors.
     */
    CAN_UNLOCK_DOORS,

    /**
     * The ability to enter floors.
     */
    CAN_ENTER_FLOORS
}
