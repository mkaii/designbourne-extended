package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.items.consumables.HealingVial;
import game.items.consumables.Runes;

/**
 * A class that represents a Red Wolf.
 * <p>A red wolf wanders around the map and attacks the player if in proximity.</p>
 * <p>A red wolf also follows the player if in proximity until either the player or itself is unconscious </p>
 * Created by:
 * @author Lok Mei Hui
 */
public class RedWolf extends Enemy{

    /**
     * Constructor for Red Wolf
     */
    public RedWolf() {
        super("Red Wolf", 'r', 25);
        this.damageMultiplier = 1.0f;
        this.intrinsicWeaponDamage = 15;
        this.intrinsicWeaponVerb = "Bites";
        this.intrinsicWeaponHitRate = 80;

        this.setDefaultBehaviours();
    }

    /**
     * Constructor for Red Wolf.
     *
     * @param hitPoints hit points of the Red Wolf
     * @param intrinsicWeaponDamage damage stat of the intrinsic weapon of the Red Wolf
     * @param damageMultiplier damage multiplier of the Red Wolf
     * @param intrinsicWeaponVerb verb of the intrinsic weapon of the Red Wolf
     * @param intrinsicWeaponHitRate hit rate of the intrinsic weapon of the Red Wolf
     */
    public RedWolf(int hitPoints, int intrinsicWeaponDamage, float damageMultiplier, String intrinsicWeaponVerb, int intrinsicWeaponHitRate) {
        super("Red Wolf", 'r', hitPoints);
        this.damageMultiplier = damageMultiplier;
        this.intrinsicWeaponDamage = intrinsicWeaponDamage;
        this.intrinsicWeaponVerb = intrinsicWeaponVerb;
        this.intrinsicWeaponHitRate = intrinsicWeaponHitRate;

        this.setDefaultBehaviours();
    }

    /**
     * This method adds the default behaviours of the Red Wolf to the map of behaviours.
     * <p>
     *     The default behaviour of the Red Wolf is to wander around the map and attack the player if in proximity.
     *     The Red Wolf will also follow the player if in proximity.
     * </p>
     */
    private void setDefaultBehaviours() {
        this.getBehaviours().put(0, new AttackBehaviour());
        this.getBehaviours().put(1, new FollowBehaviour());
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int damage = Math.round(intrinsicWeaponDamage * this.damageMultiplier);;

        return new IntrinsicWeapon(damage, this.intrinsicWeaponVerb, this.intrinsicWeaponHitRate);
    }

    /**
     * This method performs logic that is this enemy is defeated by the player.
     *
     * <p>Red Wolf has 10% chance to drop a healing vial when defeated.</p>
     * <p>Red Wolf will drop 25 runes when defeated.</p>
     *
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */

    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(this);
        String returnStr = super.unconscious(actor, map);

        if (Math.random() <= 0.10) {
            currentLocation.addItem(new HealingVial());
            returnStr += "\n" + this.name + " dropped a Healing Vial!";
        }

        currentLocation.addItem(new Runes(25));
        returnStr += "\n" + this.name + " dropped 25 Runes";

        return returnStr;
    }

    /**
     * This method returns a new instance of Red Wolf with the same attributes as this instance.
     *
     * <p>This method can be used by spawners to get copies of this Red Wolf.</p>
     *
     * @return a new instance of Red Wolf
     */
    @Override
    public Enemy getNewInstance() {
        return new RedWolf(
                this.getAttributeMaximum(BaseActorAttributes.HEALTH),
                this.intrinsicWeaponDamage,
                this.damageMultiplier,
                this.intrinsicWeaponVerb,
                this.intrinsicWeaponHitRate
        );
    }
}
