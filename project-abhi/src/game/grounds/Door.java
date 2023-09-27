package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelToNewMapAction;
import game.actions.UnlockDoorAction;
import game.capabilities.Ability;

/**
 * A class that represents a door.
 * Created by:
 * @author Abhijit Upadhyay
 */
public class Door extends Ground {
    /**
     * A boolean to keep track if the door is unlocked or not
     */
    private boolean isUnlocked;

    /**
     * The next map that the door leads to
     */
    private GameMap nextMap;

    /**
     * Constructor for Door leading to different maps
     *
     * @param nextMap the next map that the door leads to
     */
    public Door(GameMap nextMap) {
        super('=');
        isUnlocked = false;
        this.nextMap = nextMap;
    }

    /**
     * Method to check if the actor can enter the door
     *
     * @param actor the Actor to check
     * @return true if the door is unlocked, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return this.isUnlocked;
    }

    /**
     * Returns a list of actions that the actor can perform on the door
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new ActionList object containing the actions that the actor can perform on the door
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = super.allowableActions(actor, location, direction);

        if (!this.isUnlocked) {
            actions.add(new UnlockDoorAction(this));
        } else {
            actions.add(new TravelToNewMapAction(this.nextMap));
        }

        return actions;
    }


    /**
     * Method to unlock the door
     *
     * @param actor the Actor trying to unlock the door
     * @return a string describing the result of the action
     */
    public String unlock(Actor actor) {
        String returnStr;

        if (actor.hasCapability(Ability.CAN_UNLOCK_DOORS)) {
            this.isUnlocked = true;
            returnStr = actor + " unlocks the door.";
        } else {
            returnStr = actor + " needs Old Key to unlock the door.";
        }

        return returnStr;
    }
}
