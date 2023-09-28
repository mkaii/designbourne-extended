package game.items.skillweapons.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateWeaponSkillAction;
import game.actions.AttackAction;
import game.capabilities.Status;
import game.items.skillweapons.skilltypes.Focus;
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
        String output = "";

        if (buyer.getBalance() < price) {
            output += buyer + " does not have enough runes to buy " + this + " from " + seller + ".";
        }
        else {
            buyer.deductBalance(price);
            seller.addBalance(price);

            buyer.addItemToInventory(new BroadSword());
            output += buyer + " bought " + this + " from " + seller + " for " + price + " runes.";
        }


        return output;
    }

    @Override
    public String sell(Actor seller, Actor buyer, int price) {
        seller.removeItemFromInventory(this);
        seller.addBalance(price);
        buyer.deductBalance(price);

        return seller + " sold " + this + " to " + buyer + " for " + price + " runes.";
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
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }

        return actions;
    }


}
