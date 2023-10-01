package game.items.skillweapons.skilltypes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.items.skillweapons.weapons.SkillWeapon;

public class GreatSlam extends WeaponSkill{

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


    public GreatSlam(SkillWeapon weapon, float damageBonus, int hitRate) {
        super("Great Slam", weapon);
        this.damageBonus = damageBonus/100;
        this.hitRate = hitRate;
        this.weaponInitialHitRate = weapon.chanceToHit();
    }


    @Override
    public String activate(Actor actor) {

        int staminaCost = (int) Math.round(actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.05);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, staminaCost);
        this.isActive = true;
        return "Great Slam activated on " + getWeapon() + "!";
    }

    @Override
    public String deactivate() {
        isActive = false;
        return "Grand Slam Skill is deactivated!";
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
        if (actorMaxStamina * 0.05 <= actorStamina) {
            actorCanActivate = true;
        }

        return actorCanActivate;
    }

}
