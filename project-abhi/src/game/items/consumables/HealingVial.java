package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.items.trade.TradeItem;
import game.items.trade.Tradeable;


/**
 * Class representing a Healing Vial
 *
 * <p>Healing Vial can be consumed by an actor to restore health.</p>
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class HealingVial extends ConsumableItem implements Tradeable {

    /**
     * The percentage of max health to be restored upon consumption.
     */
    private float restoreBy;

    /**
     * Constructor for HealingVial
     */
    public HealingVial() {
        super("Healing Vial", 'a', true);
        this.restoreBy = 0.1f;
    }

    /**
     * Constructor for HealingVial that takes in a percentage of max health to restore
     *
     * <p>For example, new HealingVial(20.5f)</p>
     *
     * @param restoreByPercentage the percentage of max health to restore
     */
    public HealingVial(float restoreByPercentage) {
        super("Healing Vial", 'a', true);
        this.restoreBy = restoreByPercentage/100;
    }


    /**
     * This method applies the effects of consumption of this item to the actor
     *
     * <p>Restores a percentage of max health.</p>
     *
     * @param actor the actor that consuming this item
     * @return a string describing the effects of consumption
     */
    @Override
    public String consume(Actor actor) {
        int maxHealth = actor.getAttributeMaximum(BaseActorAttributes.HEALTH);
        actor.heal((int) (maxHealth * this.restoreBy));

        actor.removeItemFromInventory(this);

        return actor + " consumes " + this + " and heals " + this.restoreBy * 100 + " of max HP.";
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);

        if (otherActor.hasCapability(Status.TRADER)) {
            actions.add(new TradeItem(this, this.getValue()).getSellAction(otherActor));
        }

        return actions;
    }

    /**
     * Method to get the resale value of this item
     * @return the resale value of this item
     */
    @Override
    public int getValue() {
        return 35;
    }


    /**
     * Method to buy this item from another actor
     *
     * @param seller the actor selling this item
     * @param buyer the actor buying this item
     * @param price the price at which this item is being bought
     * @return a string describing the result of the transaction
     */
    @Override
    public String buy(Actor seller, Actor buyer, int price) {
        String result = "";

        if (Math.random() <= 0.25) {
            price *= 1.5;
            result += seller + " is charging 50% more for " + this + "\n";
        }

        if (buyer.getBalance() >= price) {
            buyer.addItemToInventory(new HealingVial(this.restoreBy * 100));
            buyer.deductBalance(price);
            seller.addBalance(price);
            result += buyer + " bought " + this + " from " + seller + " for " + price + " runes.";
        } else {
            result += buyer + " does not have enough runes to buy " + this + " from " + seller + ".";
        }

        return result;
    }

    /**
     * Method to sell this item to another actor
     *
     * @param seller the actor selling this item
     * @param buyer the actor buying this item
     * @param price the price at which this item is being sold
     * @return a string describing the result of the transaction
     */
    @Override
    public String sell(Actor seller, Actor buyer, int price) {
        String output = "";

        if (Math.random() <= 0.10) {
            price *= 2;
            output += buyer + " is paying 2x the price for " + this + "\n";
        }

        seller.removeItemFromInventory(this);
        seller.addBalance(price);
        buyer.deductBalance(price);
        buyer.addItemToInventory(new HealingVial());
        output += seller + " sold " + this + " to " + buyer + " for " + price + " runes.";

        return output;
    }

}
