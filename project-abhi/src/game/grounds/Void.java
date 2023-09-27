package game.grounds;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * A class that represents a void.
 * Created by:
 * @author Abhijit Upadhyay
 */
public class Void extends Ground {
    /**
     * Constructor for Void.
     * <p>Any Actor that steps on void gets eliminated.</p>
     */
    public Void() {
        super('+');
    }

    /**
     * Check if the location contains an Actor, if it does, the Actor gets eliminated.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            String message = actor.unconscious(location.map());
            Display d = new Display();
            d.println(message);
        }
    }
}
