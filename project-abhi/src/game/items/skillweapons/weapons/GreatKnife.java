package game.items.skillweapons.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.skillweapons.skilltypes.Focus;
import game.items.skillweapons.skilltypes.StabAndStep;
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


        return output;
    }

    @Override
    public String sell(Actor seller, Actor buyer, int price) {
        seller.removeItemFromInventory(this);
        seller.addBalance(price);
        buyer.deductBalance(price);

        return seller + " sold " + this + " to " + buyer + " for " + price + " runes.";
    }
}
