package game.items.skillweapons.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.skillweapons.skilltypes.Focus;
import game.items.skillweapons.skilltypes.GreatSlam;
import game.items.trade.Tradeable;

public class GiantHammer extends SkillWeapon implements Tradeable {

    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "Slashes", 90);
        this.setWeaponSkill(new GreatSlam(this, 1, 90));
    }

    @Override
    public int getValue() {
        return 250;
    }

    @Override
    public String buy(Actor seller, Actor buyer, int price) {
        return null;
    }

    @Override
    public String sell(Actor seller, Actor buyer, int price) {
        return null;
    }
}
