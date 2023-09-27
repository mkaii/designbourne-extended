package game.items.skillweapons.skilltypes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.skillweapons.weapons.SkillWeapon;

/**
 * A class representing the Focus weapon skill.
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class Focus extends WeaponSkill {

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
     * Constructor for Focus weapon skill.
     *
     * @param weapon      the weapon that has this skill
     * @param damageBonus the damage bonus to be applied to the weapon upon activation
     * @param hitRate     the hit rate to be applied to the weapon upon activation
     */
    public Focus(SkillWeapon weapon, float damageBonus, int hitRate) {
        super("Focus", weapon);
        this.damageBonus = damageBonus/100;
        this.hitRate = hitRate;
        this.weaponInitialHitRate = weapon.chanceToHit();
    }

    /**
     * Activates the Focus weapon skill.
     *
     * <p>Increases the weapon's damage multiplier by 10% and sets the hit rate to 90% for 5 turns.</p>
     *
     * @param actor the actor that is activating the weapon skill
     * @return a string describing the result of activating the weapon skill
     */
    @Override
    public String activate(Actor actor) {
        this.isActive = true;
        this.counter = 0;
        WeaponItem weapon = this.getWeapon();
        weapon.updateHitRate(this.hitRate);
        weapon.increaseDamageMultiplier(this.damageBonus);

        int staminaCost = (int) Math.round(actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.2);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, staminaCost);

        return "Focus activated on " + getWeapon() + "!";
    }

    /**
     * Deactivates the Focus weapon skill.
     *
     * <p>Resets the stats for the weapon</p>
     *
     * @return a string describing the result of deactivating the weapon skill
     */
    @Override
    public String deactivate() {
        if (this.isActive) {
            this.isActive = false;
            this.counter = 0;
            WeaponItem weapon = this.getWeapon();
            weapon.updateHitRate(this.weaponInitialHitRate);
            // should you the default stat for the weapon but there is no getter to get the default stat
            weapon.updateDamageMultiplier(1);
            return "Focus deactivated on " + getWeapon() + "!";
        }
        return "";
    }

    /**
     * This method is called every turn to keep track of the number of turns the weapon skill has been activated.
     *
     * <p>Focus skill lasts for 5 turns.</p>
     */
    @Override
    public void tick() {
        if (this.isActive) {
            this.counter++;
            if (this.counter == 5) {
                this.deactivate();
            }
        }
    }

    /**
     * Checks if Focus can be activated by the owner of the weapon.
     *
     * <p>Owner must have at least 20% of max stamina to activate Focus.</p>
     *
     * @param actor the actor that is trying to activate the weapon skill
     * @return true if the weapon skill can be activated, false otherwise
     */
    @Override
    public boolean canActivate(Actor actor) {
        boolean actorCanActivate = false;
        int actorStamina = actor.getAttribute(BaseActorAttributes.STAMINA);
        int actorMaxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);

        // Actor can activate the weapon skill if the actor's stamina is at least 20% of the actor's max stamina
        if (actorMaxStamina * 0.2 <= actorStamina) {
            actorCanActivate = true;
        }

        return actorCanActivate;
    }
}
