package game.items.skillweapons.weapons;

import game.items.skillweapons.skilltypes.Focus;
import game.items.skillweapons.skilltypes.StabAndStep;

public class GreatKnife extends SkillWeapon{



    public GreatKnife() {
        super("Great Knife", '>', 75, "Slashes", 70);
        this.setWeaponSkill(new StabAndStep(this, 0, 70));
    }
}
