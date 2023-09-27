package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.capabilities.Ability;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Abhijit Upadhyay
 *
 */
public class Floor extends Ground {

    /**
     * Constructor for Floor.
     */
    public Floor() {
        super('_');
    }

    /**
     * Only the actors with the ability to enter floors can enter the floor.
     *
     * @param actor the Actor to check
     * @return true if the Actor has the ability to enter floors, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Ability.CAN_ENTER_FLOORS)) {
            return true;
        }
        return false;
    }
}
