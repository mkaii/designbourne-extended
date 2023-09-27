package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.capabilities.Status;
import java.util.List;

/**
 * A class that represents the Attack behaviour of NPCs.
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Returns an AttackAction if the target is next to the Actor, otherwise null.
     * <p>Checks all exits of this actor and returns attack action if player is spotted.</p>
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Exit> exits = map.locationOf(actor).getExits();
        Location destination;

        for (Exit e: exits) {
            destination = e.getDestination();

            if (destination.containsAnActor()) {
                if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(destination.getActor(), e.getName());
                }
            }
        }
        return null;
    }
}
