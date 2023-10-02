/**
 * A class representing the "Stab and Step" skill for a skill weapon.
 * This skill allows the activation of a special stabbing attack with the ability to step aside.
 */
package game.items.skillweapons.skilltypes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.items.skillweapons.weapons.SkillWeapon;

public class StabAndStep extends WeaponSkill {

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
    private final SkillWeapon weapon;

    /**
     * Constructor for the StabAndStep class.
     *
     * @param weapon      The skill weapon associated with this skill.
     * @param damageBonus The damage bonus to be applied upon activation (as a percentage).
     * @param hitRate     The hit rate to be applied upon activation.
     */
    public StabAndStep(SkillWeapon weapon, float damageBonus, int hitRate) {
        super("Stab And Step", weapon);
        this.damageBonus = damageBonus / 100;
        this.hitRate = hitRate;
        this.weapon = weapon;
    }

    /**
     * Activates the "Stab and Step" skill.
     *
     * @param actor The actor activating the skill.
     * @return A string describing the activation of the skill.
     */
    @Override
    public String activate(Actor actor) {
        this.isActive = true;

        // Reduce stamina:
        int staminaCost = (int) Math.round(actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.25);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, staminaCost);

        return "Stab and Step activated on " + getWeapon() + "!";
    }

    /**
     * Deactivates the "Stab and Step" skill.
     *
     * @return A string describing the deactivation of the skill.
     */
    @Override
    public String deactivate() {
        isActive = false;
        return "Stab and Step skill is deactivated";
    }

    /**
     * Performs an action during each game tick (unused in this skill).
     */
    @Override
    public void tick() {
        // Unused in this skill
    }

    /**
     * Determines if the "Stab and Step" skill can be activated by the actor.
     *
     * @param actor The actor attempting to activate the skill.
     * @return True if the actor can activate the skill; otherwise, false.
     */
    @Override
    public boolean canActivate(Actor actor) {
        boolean actorCanActivate = false;

        int actorStamina = actor.getAttribute(BaseActorAttributes.STAMINA);
        int actorMaxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);

        // Actor can activate the weapon skill if the actor's stamina is at least 20% of the actor's max stamina
        if (actorMaxStamina * 0.25 <= actorStamina) {
            actorCanActivate = true;
        }

        return actorCanActivate;
    }
}
