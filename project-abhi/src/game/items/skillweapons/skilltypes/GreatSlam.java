/**
 * A class representing the "Great Slam" skill for a skill weapon.
 * This skill allows the activation of a powerful slam attack with a damage bonus.
 */
package game.items.skillweapons.skilltypes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.items.skillweapons.weapons.SkillWeapon;

public class GreatSlam extends WeaponSkill {

    /**
     * The damage bonus to be applied to the weapon upon activation.
     */
    private float damageBonus;

    /**
     * The hit rate to be applied to the weapon upon activation.
     */
    private int hitRate;

    /**
     * The initial hit rate of the weapon.
     */
    private final int weaponInitialHitRate;

    /**
     * Constructor for the GreatSlam class.
     *
     * @param weapon      The skill weapon associated with this skill.
     * @param damageBonus The damage bonus to be applied upon activation (as a percentage).
     * @param hitRate     The hit rate to be applied upon activation.
     */
    public GreatSlam(SkillWeapon weapon, float damageBonus, int hitRate) {
        super("Great Slam", weapon);
        this.damageBonus = damageBonus / 100;
        this.hitRate = hitRate;
        this.weaponInitialHitRate = weapon.chanceToHit();
    }

    /**
     * Activates the "Great Slam" skill.
     *
     * @param actor The actor activating the skill.
     * @return A string describing the activation of the skill.
     */
    @Override
    public String activate(Actor actor) {
        int staminaCost = (int) Math.round(actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.05);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, staminaCost);
        this.isActive = true;
        return "Great Slam activated on " + getWeapon() + "!";
    }

    /**
     * Deactivates the "Great Slam" skill.
     *
     * @return A string describing the deactivation of the skill.
     */
    @Override
    public String deactivate() {
        isActive = false;
        return "Grand Slam Skill is deactivated!";
    }

    /**
     * Performs an action during each game tick (unused in this skill).
     */
    @Override
    public void tick() {
        // Unused in this skill
    }

    /**
     * Determines if the "Great Slam" skill can be activated by the actor.
     *
     * @param actor The actor attempting to activate the skill.
     * @return True if the actor can activate the skill; otherwise, false.
     */
    @Override
    public boolean canActivate(Actor actor) {
        boolean actorCanActivate = false;
        int actorStamina = actor.getAttribute(BaseActorAttributes.STAMINA);
        int actorMaxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);

        // Actor can activate the weapon skill if the actor's stamina is at least 5% of the actor's max stamina
        if (actorMaxStamina * 0.05 <= actorStamina) {
            actorCanActivate = true;
        }

        return actorCanActivate;
    }
}
