package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.items.consumables.HealingVial;
import game.items.consumables.RefreshingFlask;
import game.items.consumables.Runes;

/**
 * A class that represents a Hollow Soldier.
 * <p>A hollow soldier wanders around the map and attacks the player if in proximity.</p>
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class HollowSoldier extends Enemy {

    /**
     * Constructor for Hollow Soldier
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);
        this.damageMultiplier = 1.0f;
        this.intrinsicWeaponDamage = 50;
        this.intrinsicWeaponVerb = "Whacks";
        this.intrinsicWeaponHitRate = 50;

        this.setDefaultBehaviours();
    }

    /**
     * Constructor for Hollow Soldier.
     *
     * @param hitPoints hit points of the Hollow Soldier
     * @param intrinsicWeaponDamage damage stat of the intrinsic weapon of the Hollow Soldier
     * @param damageMultiplier damage multiplier of the Hollow Soldier
     * @param intrinsicWeaponVerb verb of the intrinsic weapon of the Hollow Soldier
     * @param intrinsicWeaponHitRate hit rate of the intrinsic weapon of the Hollow Soldier
     */
    public HollowSoldier(int hitPoints, int intrinsicWeaponDamage, float damageMultiplier, String intrinsicWeaponVerb, int intrinsicWeaponHitRate) {
        super("Hollow Soldier", '&', hitPoints);
        this.damageMultiplier = damageMultiplier;
        this.intrinsicWeaponDamage = intrinsicWeaponDamage;
        this.intrinsicWeaponVerb = intrinsicWeaponVerb;
        this.intrinsicWeaponHitRate = intrinsicWeaponHitRate;

        this.setDefaultBehaviours();
    }

    /**
     * This method adds the default behaviours of the Hollow Soldier to the map of behaviours.
     * <p>
     *     The default behaviour of the Hollow Soldier is to wander around the map and attack the player if in proximity.
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
        int damage = Math.round(intrinsicWeaponDamage * this.damageMultiplier);;

        return new IntrinsicWeapon(damage, this.intrinsicWeaponVerb, this.intrinsicWeaponHitRate);
    }

    /**
     * This method performs logic that is this enemy is defeated by the player.
     *
     * <p>Hollow Soldier has 20% chance of dropping a healing vile and a 30% chance to drop a refreshing flask when defeated.</p>
     * <p>Hollow Soldier will also drop 100 Runes when defeated</p>
     *
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(this);
        String returnStr = super.unconscious(actor, map);

        if (Math.random() <= 0.20) {
            currentLocation.addItem(new HealingVial());
            returnStr += "\n" + this.name + " dropped a Healing Vial!";
        }

        if (Math.random() <= 0.30) {
            currentLocation.addItem(new RefreshingFlask());
            returnStr += "\n" + this.name + " dropped a Refreshing Flask!";
        }

        currentLocation.addItem(new Runes(100));
        returnStr += "\n" + this.name + " dropped 100 Runes";

        return returnStr;
    }

    /**
     * This method returns a new instance of Hollow Soldier with the same attributes as this instance.
     *
     * <p>This method can be used by spawners to get copies of this Hollow Soldier.</p>
     *
     * @return a new instance of Hollow Soldier
     */
    @Override
    public Enemy getNewInstance() {
        return new HollowSoldier(
                this.getAttributeMaximum(BaseActorAttributes.HEALTH),
                this.intrinsicWeaponDamage,
                this.damageMultiplier,
                this.intrinsicWeaponVerb,
                this.intrinsicWeaponHitRate
        );
    }
}
