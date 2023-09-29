package game.items.skillweapons.skilltypes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.skillweapons.weapons.SkillWeapon;

/**
 * This class serves as parent class for all weapon skills.
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public abstract class WeaponSkill {
    /**
     * The weapon that has this skill.
     */
    private SkillWeapon weapon;

    /**
     * The counter for the weapon skill to keep track of the number of turns the skill has been activated.
     */
    protected int counter = 0;

    /**
     * The name of the weapon skill.
     */
    private String name;

    /**
     * The boolean to keep track of whether the weapon skill is active or not.
     */
    public boolean isActive;

    /**
     * Constructor for WeaponSkill.
     *
     * @param name the name of the weapon skill
     * @param weapon the weapon that has this skill
     */
    public WeaponSkill(String name, SkillWeapon weapon) {
        this.name = name;
        this.weapon = weapon;
        this.isActive = false;
    }

    /**
     * Method to activate the weapon skill.
     *
     * @param actor the actor that owns the weapon with this skill
     * @return a string that describes the activation of the weapon skill
     */
    public abstract String activate(Actor actor);

    /**
     * Method to deactivate the weapon skill.
     *
     * @return a string that describes the deactivation of the weapon skill
     */
    public abstract String deactivate();

    /**
     * Method that is called every turn to check if the weapon skill's active duration has ended.
     */
    public abstract void tick();

    /**
     * Returns the name of the weapon skill.
     *
     * @return the name of the weapon skill
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns whether the owner of the weapon can activate the weapon skill.
     *
     * @param actor the actor that owns the weapon with this skill
     * @return true if the actor can activate the weapon skill, false otherwise
     */
    public abstract boolean canActivate(Actor actor);

    /**
     * Returns the weapon that has this skill.
     *
     * @return the weapon that has this skill
     */
    public WeaponItem getWeapon() {
        return this.weapon;
    }

}
