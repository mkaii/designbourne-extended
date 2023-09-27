package game.items.skillweapons.weapons;

import game.items.skillweapons.skilltypes.Focus;
import game.items.skillweapons.skilltypes.GreatSlam;

public class GiantHammer extends SkillWeapon{

    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "Slashes", 90);
        this.setWeaponSkill(new GreatSlam(this, 1, 90));
    }
}
