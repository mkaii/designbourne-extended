/**
 * A class representing a Giant Hammer, which is a skill-based weapon that can perform powerful slamming attacks.
 * It implements the Tradeable interface, allowing it to be traded between actors in the game.
 */
package game.items.skillweapons.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateWeaponSkillAction;
import game.actions.GreatSlamAttackAction;
import game.capabilities.Status;
import game.items.skillweapons.skilltypes.GreatSlam;
import game.items.skillweapons.skilltypes.WeaponSkill;
import game.items.trade.TradeItem;
import game.items.trade.Tradeable;

public class GiantHammer extends SkillWeapon implements Tradeable {

    /**
     * Constructor for the GiantHammer class.
     * Initializes the Giant Hammer with its name, display character, base damage, and skill.
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "Slashes", 90);
        this.setWeaponSkill(new GreatSlam(this, 1, 90));
    }

    /**
     * Retrieves the value of the Giant Hammer.
     *
     * @return The value of the Giant Hammer in runes.
     */
    @Override
    public int getValue() {
        return 250;
    }

    /**
     * Buy method for trading the Giant Hammer.
     *
     * @param seller The actor selling the Giant Hammer.
     * @param buyer  The actor buying the Giant Hammer.
     * @param price  The price at which the Giant Hammer is being sold.
     * @return A string describing the outcome of the trade.
     */
    @Override
    public String buy(Actor seller, Actor buyer, int price) {
        String result = "";

        // Implementation of the buy method (You can add your implementation here)

        return result;
    }

    /**
     * Sell method for trading the Giant Hammer.
     *
     * @param seller The actor selling the Giant Hammer.
     * @param buyer  The actor buying the Giant Hammer.
     * @param price  The price at which the Giant Hammer is being sold.
     * @return A string describing the outcome of the trade.
     */
    @Override
    public String sell(Actor seller, Actor buyer, int price) {
        String output = "";

        // Calculate the amount the player will receive if the buyer doesn't take it.
        int receivedRunes = getValue();

        // Check if the player has less than 250 runes.
        if (seller.getBalance() < receivedRunes) {
            receivedRunes = seller.getBalance();
        }

        // Determine if the buyer takes the runes.
        boolean buyerTakesRunes = Math.random() <= 0.10;

        if (buyerTakesRunes) {
            // If the buyer takes the runes, deduct them from the seller's balance.
            seller.deductBalance(receivedRunes);
            output += "The buyer took " + receivedRunes + " runes from " + seller + ".\n";
        } else {
            // If the buyer doesn't take the runes, add the received amount to the seller's balance.
            seller.addBalance(receivedRunes);
            output += seller + " received " + receivedRunes + " runes from selling " + this + " to " + buyer + ".\n";
        }

        seller.removeItemFromInventory(this);
        return output;
    }

    /**
     * Retrieves the list of allowable actions that the Giant Hammer allows its owner to perform.
     *
     * @param owner The actor who owns the Giant Hammer.
     * @return An ActionList containing allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = super.allowableActions(owner);
        WeaponSkill skill = this.getWeaponSkill();

        if (skill != null && skill.canActivate(owner)) {
            actions.add(new ActivateWeaponSkillAction(this));
        }
        return actions;
    }

    /**
     * Retrieves the list of allowable actions that the weapon allows its owner to do to other actors.
     *
     * @param otherActor The other actor.
     * @param location   The location of the other actor.
     * @return An unmodifiable list of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);

        if (otherActor.hasCapability(Status.TRADER)) {
            actions.add(new TradeItem(this, this.getValue()).getSellAction(otherActor));
        } else {
            actions.add(new GreatSlamAttackAction(otherActor, location.toString(), this));
        }

        return actions;
    }
}
