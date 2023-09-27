package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.items.trade.Tradeable;

/**
 * Class representing a Bloodberry
 *
 * <p>Bloodberry can be consumed by an actor to restore health.</p>
 *
 * Created by:
 * @author Lok Mei Hui
 */
public class Bloodberry extends ConsumableItem implements Tradeable {

    /**
     * Constructor for Bloodberry
     */
    public Bloodberry() {
        super("Bloodberry", '*', true);
    }

    /**
     * This method applies the effects of consumption of this item to the actor
     *
     * <p>Increase the max health.</p>
     *
     * @param actor the actor that consuming this item
     * @return a string describing the effects of consumption
     */
    @Override
    public String consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,5);
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,5);

        actor.removeItemFromInventory(this);

        return actor + " consumes " + this ;
    }

    /**
     * This method returns the resale value of the item
     *
     * @return the resale value of the item
     */
    @Override
    public int getValue() {
        return 10;
    }

    /**
     * Method to execute the logic of buying an item
     *
     * @param seller the actor selling the item
     * @param buyer  the actor buying the item
     * @param price  the price at which the item is being bought
     * @return a string describing the result of the transaction
     */
    @Override
    public String buy(Actor seller, Actor buyer, int price) {
        return null;
    }

    /**
     * Method to execute the logic of selling an item
     *
     * @param seller the actor selling the item
     * @param buyer  the actor buying the item
     * @param price  the price at which the item is being sold
     * @return a string describing the result of the transaction
     */
    @Override
    public String sell(Actor seller, Actor buyer, int price) {
        seller.deductBalance(price);
        buyer.addBalance(price);
        seller.removeItemFromInventory(this);
        buyer.addItemToInventory(this);
        return seller + " sold " + this + " to " + buyer + " for " + price + " runes.";
    }
}
