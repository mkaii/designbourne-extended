package game.items.skillweapons.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.DropSkillWeaponAction;
import game.items.skillweapons.skilltypes.WeaponSkill;


/**
 * This class represents a skill weapon.
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public abstract class SkillWeapon extends WeaponItem {

    /**
     * The skill of this weapon.
     */
    private WeaponSkill skill;

    /**
     * Constructor for SkillWeapon.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public SkillWeapon(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    /**
     * This method returns the skill of this weapon.
     *
     * @return the skill of this weapon.
     */
    public WeaponSkill getWeaponSkill() {
        return this.skill;
    }

    /**
     * This method sets the skill of this weapon.
     *
     * @param skill the skill that this weapon will have.
     */
    public void setWeaponSkill(WeaponSkill skill) {
        if (this.skill != null){
            this.skill.deactivate();
        }

        if (skill.getWeapon() == this){
            this.skill = skill;
        }
    }

    /**
     * Create and return an action to drop this skill weapon.
     * @return a new DropItemAction if this Item is portable, null otherwise.
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        return new DropSkillWeaponAction(this);
    }
}
