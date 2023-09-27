package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;

import java.util.List;

/**
 * A class that represents the Follow behaviour of NPCs.
 *
 * Created by:
 * @author Lok Mei Hui
 */
public class FollowBehaviour implements Behaviour {

    /**
     * Returns a MoveAction to follow the player.
     * If no movement is possible, returns null.
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
                    Actor player = destination.getActor();

                    if(actor.isConscious() && player.isConscious()){
                        return e.getDestination().getMoveAction(actor, "to follow", e.getHotKey());
                    }

                }
            }
        }
        return null;

    }
}
