package game.items.skillweapons.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateWeaponSkillAction;
import game.actions.StabAndMoveAttackAction;
import game.capabilities.Status;
import game.items.skillweapons.skilltypes.StabAndStep;
import game.items.skillweapons.skilltypes.WeaponSkill;
import game.items.trade.TradeItem;
import game.items.trade.Tradeable;

public class GreatKnife extends SkillWeapon implements Tradeable {



    public GreatKnife() {
        super("Great Knife", '>', 75, "Slashes", 70);
        this.setWeaponSkill(new StabAndStep(this, 0, 70));
    }

    @Override
    public int getValue() {
        return 175;
    }

    @Override
    public String buy(Actor seller, Actor buyer, int price) {
        String result = "";

        if (Math.random() <= 0.05) {
            price *= 3;
            result += seller + " is charging 3 times more for " + this + "\n";
        }

        if (buyer.getBalance() >= price) {
            buyer.addItemToInventory(new GreatKnife());
            buyer.deductBalance(price);
            seller.addBalance(price);
            result += buyer + " bought " + this + " from " + seller + " for " + price + " runes.";
        } else {
            result += buyer + " does not have enough runes to buy " + this + " from " + seller + ".";
        }


        return result;
    }

    @Override
    public String sell(Actor seller, Actor buyer, int price) {
        String output = "";

        // Calculate the amount the player will receive if the traveler doesn't take it.
        int receivedRunes = getValue();

        // Check if the player has less than 175 runes.
        if (seller.getBalance() < receivedRunes) {
            receivedRunes = seller.getBalance();
        }

        // Determine if the traveler takes the runes.
        boolean travelerTakesRunes = Math.random() <= 0.10;

        if (travelerTakesRunes) {
            // If the traveler takes the runes, deduct them from the player's balance.
            seller.deductBalance(receivedRunes);
            output += "The traveler took " + receivedRunes + " runes from " + seller + ".\n";
        } else {
            // If the traveler doesn't take the runes, add the received amount to the player's balance.
            seller.addBalance(receivedRunes);
            output += seller + " received " + receivedRunes + " runes from selling " + this + " to " + buyer + ".\n";
        }

        buyer.addItemToInventory(this);
        output += seller + " sold " + this + " to " + buyer + " for " + receivedRunes + " runes.";

        return output;
    }


    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);
        WeaponSkill skill = this.getWeaponSkill();

        if (skill != null && skill.canActivate(owner)) {
            actions.add(new ActivateWeaponSkillAction(this));
        }
        return actions;
    }

    /**
     * List of allowable actions that the weapon allows its owner do to other actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);

        if (otherActor.hasCapability(Status.TRADER)) {
            actions.add(new TradeItem(this, this.getValue()).getSellAction(otherActor));
        } else {
            actions.add(new StabAndMoveAttackAction(otherActor, location.toString(), this));//override this attack action to move as well
        }

        return actions;
    }


}
