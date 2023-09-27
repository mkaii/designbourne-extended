package game.grounds.drinkable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DrinkAction;
import game.capabilities.Status;

/**
 * A class that represents a puddle.
 *
 */
public class Puddle extends Ground implements DrinkableGround{

    /**
     * Constructor for Puddle.
     */
    public Puddle() {
        super('~');
    }

    /**
     * Returns a list of actions that the actor can perform on the puddle
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new ActionList object containing the actions that the actor can perform on the puddle
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = super.allowableActions(actor, location, direction);

        if (location.containsAnActor()) {

            // check if the actor is a player
            if(actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                actions.add(new DrinkAction(this));
            }
        }
        return actions;
    }

    /**
     * This method applies the effects of drinking this ground to the actor
     *
     * <p>Restores a percentage of max stamina</p>
     * <p>Increase the health points of player by one</p>
     *
     * @param actor the actor that drinks this ground
     * @return a string describing the effects of consumption
     */
    @Override
    public String drink(Actor actor) {

        // increase player's stamina by 1% of maximum stamina
        int newMaxStamina = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA)* 0.1f);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,newMaxStamina);

       // heals player by 1 point
        actor.heal(1);

        return actor + " drinks from puddle"  + ", restores " + newMaxStamina + "of stamina and heals 1 point" ;
    }
}
