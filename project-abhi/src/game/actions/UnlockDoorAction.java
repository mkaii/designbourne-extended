package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Door;

/**
 * Action that allows the player to unlock a door.
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class UnlockDoorAction extends Action {

    /**
     * The door to unlock.
     */
    private Door door;

    /**
     * Constructor.
     *
     * @param door the door to unlock
     */
    public UnlockDoorAction(Door door) {
        this.door = door;
    }

    /**
     * Unlock the door.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String returnStr = this.door.unlock(actor);
        return returnStr;
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string containing the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks the door.";
    }
}
