package game.capabilities;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class
 * Created by:
 * @author Riordan D. Alfredo
 */
public enum Status {
    /**
     * The status of the player so that the player can be attacked by the enemies.
     */
    HOSTILE_TO_ENEMY,

    /**
     * The status of traders in the game.
     */
    TRADER,
}
