package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.skillweapons.weapons.SkillWeapon;

/**
 * Action for dropping a SkillWeapon.
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class DropSkillWeaponAction extends DropAction {
    /**
     * The SkillWeapon to drop.
     */
    private SkillWeapon weapon;

    /**
     * Constructor.
     *
     * @param weapon the weapon to drop
     */
    public DropSkillWeaponAction(SkillWeapon weapon) {
        super(weapon);
        this.weapon = weapon;
    }

    /**
     * Drop the weapon and deactivate its weapon skill.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = super.execute(actor, map);
        weapon.getWeaponSkill().deactivate();
        return result;
    }
}
