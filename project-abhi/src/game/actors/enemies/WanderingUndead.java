package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.items.OldKey;
import game.items.consumables.HealingVial;
import game.items.consumables.Runes;

/**
 * A class that represents a wandering undead.
 * <p>A wandering undead that wanders around the map and attacks the player if in proximity.</p>
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class WanderingUndead extends Enemy {

    /**
     * Constructor for Wandering Undead
     */
    public WanderingUndead() {
        super("Wandering Undead", 't', 100);
        this.intrinsicWeaponDamage = 30;
        this.damageMultiplier = 1.0f;
        this.intrinsicWeaponVerb = "Whacks";
        this.intrinsicWeaponHitRate = 50;

        this.setDefaultBehaviours();
    }

    /**
     * Constructor for Wandering Undead.
     *
     * @param hitPoints the hit points of the Wandering Undead
     * @param intrinsicWeaponDamage the damage stat of the intrinsic weapon of the Wandering Undead
     * @param damageMultiplier the damage multiplier of the Wandering Undead
     * @param intrinsicWeaponVerb the verb of the intrinsic weapon of the Wandering Undead
     * @param intrinsicWeaponHitRate the hit rate of the intrinsic weapon of the Wandering Undead
     */
    public WanderingUndead(int hitPoints, int intrinsicWeaponDamage, float damageMultiplier, String intrinsicWeaponVerb, int intrinsicWeaponHitRate) {
        super("Wandering Undead", 't', hitPoints);
        this.intrinsicWeaponDamage = intrinsicWeaponDamage;
        this.damageMultiplier = damageMultiplier;
        this.intrinsicWeaponVerb = intrinsicWeaponVerb;
        this.intrinsicWeaponHitRate = intrinsicWeaponHitRate;

        this.setDefaultBehaviours();
    }

    /**
     * This method adds the default behaviours of the Wandering Undead to the map of behaviours.
     * <p>
     *     The default behaviour of the Wandering Undead is to wander around the map and attack the player if in proximity.
     * </p>
     */
    private void setDefaultBehaviours() {
        this.getBehaviours().put(0, new AttackBehaviour());
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int damage = Math.round(this.intrinsicWeaponDamage * this.damageMultiplier);

        return new IntrinsicWeapon(damage, this.intrinsicWeaponVerb, this.intrinsicWeaponHitRate);
    }

    /**
     * Wandering Undead has a 25% chance of dropping a key and a 20% chance of dropping a Healing Vial when killed.
     * Wandering Undead will also drop 50 runes when defeated
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(this);
        String returnStr = super.unconscious(actor, map);

        if (Math.random() <= 0.25) {
            currentLocation.addItem(new OldKey());
            returnStr += "\n" + this.name + " dropped a key!";
        }

        if (Math.random() <= 0.20) {
            currentLocation.addItem(new HealingVial());
            returnStr += "\n" + this.name + " dropped a Healing Vial!";
        }

        currentLocation.addItem(new Runes(50));
        returnStr += "\n" + this.name + " dropped 50 Runes";

        return returnStr;
    }

    /**
     * This method returns a new instance of Wandering Undead with the same attributes as the current instance.
     *
     * <p>This method can be used by spawners to get copies of the WanderingUndead.</p>
     *
     * @return a new instance of Wandering Undead
     */
    @Override
    public Enemy getNewInstance() {
        return new WanderingUndead(this.getAttributeMaximum(BaseActorAttributes.HEALTH),
                this.intrinsicWeaponDamage,
                this.damageMultiplier,
                this.intrinsicWeaponVerb,
                this.intrinsicWeaponHitRate);
    }

}
