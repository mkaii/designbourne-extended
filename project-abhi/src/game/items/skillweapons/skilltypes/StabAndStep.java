package game.items.skillweapons.skilltypes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.items.skillweapons.weapons.SkillWeapon;

public class StabAndStep extends WeaponSkill{


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


    public StabAndStep(SkillWeapon weapon, float damageBonus, int hitRate) {
        super("Stab And Step", weapon);
        this.damageBonus = damageBonus/100;
        this.hitRate = hitRate;
        this.weaponInitialHitRate = weapon.chanceToHit();
    }


    @Override
    public String activate(Actor actor) {
        //attack
        //wander action just like the enemy
        return null;
    }

    @Override
    public String deactivate() {
        return null;
    }

    @Override
    public void tick() {

    }

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
