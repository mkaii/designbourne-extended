package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.skillweapons.skilltypes.WeaponSkill;
import game.items.skillweapons.weapons.SkillWeapon;

/**
 * Action that activates the weapon skill of a skill weapon.
 * <p>
 *     This action is performed by the player when the player chooses to activate the weapon skill of a skill weapon.
 * </p>
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class ActivateWeaponSkillAction extends Action {

    /**
     * The skill weapon whose weapon skill is to be activated.
     */
    private SkillWeapon weapon;

    /**
     * Constructor for ActivateWeaponSkillAction.
     *
     * @param weapon the weapon whose weapon skill is to be activated
     */
    public ActivateWeaponSkillAction(SkillWeapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Activate the weapon skill of the skill weapon.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = weapon.getWeaponSkill().activate(actor);
        return result;
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string containing the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        WeaponSkill skill = weapon.getWeaponSkill();
        return "Activate " + skill.toString() + " on " + weapon.toString();
    }
}
