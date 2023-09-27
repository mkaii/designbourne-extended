package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.items.trade.TradeItem;
import game.items.trade.Tradeable;

/**
 * Class representing a Refreshing Flask
 *
 * <p>Refreshing Flask can be consumed by an actor to restore stamina.</p>
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class RefreshingFlask extends ConsumableItem implements Tradeable {

    /**
     * The percentage of max stamina to be restored upon consumption.
     */
    private float restoreBy;

    /***
     * Constructor for RefreshingFlask
     */
    public RefreshingFlask() {
        super("Refreshing Flask", 'u', true);
        this.restoreBy = 0.2f;
    }

    /**
     * Constructor for RefreshingFlask that takes in a percentage of max stamina to restore
     *
     * <p>For example, new RefreshingFlask(20.5f)</p>
     *
     * @param restoreByPercentage the percentage of max stamina to restore
     */
    public RefreshingFlask(float restoreByPercentage) {
        super("Refreshing Flask", 'u', true);
        this.restoreBy = restoreByPercentage/100;
    }


    /**
     * This method applies the effects of consumption of this item to the actor
     *
     * <p>Restores a percentage of max stamina</p>
     *
     * @param actor the actor that consuming this item
     * @return a string describing the effects of consumption
     */
    @Override
    public String consume(Actor actor) {
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, (int) (maxStamina * this.restoreBy));

        actor.removeItemFromInventory(this);

        return actor + " consumes " + this + " and restores "+ this.restoreBy * 100 + " of max stamina.";
    }

    /**
     * Method that returns a list of allowable actions that the other actor can perform on this item.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return a list of allowable actions that the other actor can perform on this item
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);

        if (otherActor.hasCapability(Status.TRADER)) {
            actions.add(new TradeItem(this, this.getValue()).getSellAction(otherActor));
        }

        return actions;
    }

    /**
     * This method returns the resale value of the item
     *
     * @return the resale value of the item
     */
    @Override
    public int getValue() {
        return 25;
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
        String output = "";

        if (Math.random() <= 0.10) {
            price = (int) (price * 0.8);
            output += seller + " is feeling generous and is offering a discount of 20% on " + this + ".\n";
        }

        if (buyer.getBalance() >= price) {
            buyer.deductBalance(price);
            seller.addBalance(price);
            buyer.addItemToInventory(new RefreshingFlask(this.restoreBy * 100));
            output += buyer + " buys " + this + " from " + seller + " for " + price + " runes.";
        } else {
            output += buyer + " does not have enough runes to buy " + this + " from " + seller + ".";
        }

        return output;
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
        String output = "";

        seller.removeItemFromInventory(this);
        buyer.addItemToInventory(this);

        if (Math.random() <= 0.50) {
            buyer.deductBalance(price);
            seller.addBalance(price);
            output += seller + " sold " + this + " to " + buyer + " for " + price + " runes.";
        } else {
            output += buyer + " is not paying for " + this + ".";
        }

        return output;
    }
}
