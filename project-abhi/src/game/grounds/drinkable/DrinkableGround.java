package game.grounds.drinkable;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing a ground object that can be drink from by an actor.
 * Implementing classes should provide a method to handle the drinking action.
 *
 * Created by:
 * @author Lok Mei Hui
 */

public interface DrinkableGround {
    /**
     * Allows an actor to drink from this drinkable ground, potentially affecting the actor's attributes or status.
     *
     * @param actor the actor performing the drinking action
     */
    String drink(Actor actor);
}




