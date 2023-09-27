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
 * A class that represents a Forest Keeper.
 * <p>A forest keeper wanders around the map and attacks the player if in proximity.</p>
 * <p>A forest keeper also follows the player if in proximity until either the player or itself is unconscious </p>
 * Created by:
 * @author Lok Mei Hui
 */
public class ForestKeeper extends Enemy{

    /**
     * Constructor for Forest Keeper
     */
    public ForestKeeper() {
        super("Forest Keeper", '8', 125);
        this.damageMultiplier = 1.0f;
        this.intrinsicWeaponDamage = 25;
        this.intrinsicWeaponVerb = "Whacks";
        this.intrinsicWeaponHitRate = 75;

        this.setDefaultBehaviours();
    }

    /**
     * Constructor for Forest Keepers.
     *
     * @param hitPoints hit points of the Forest Keeper
     * @param intrinsicWeaponDamage damage stat of the intrinsic weapon of the Forest Keeper
     * @param damageMultiplier damage multiplier of the Forest Keeper
     * @param intrinsicWeaponVerb verb of the intrinsic weapon of the Forest Keeper
     * @param intrinsicWeaponHitRate hit rate of the intrinsic weapon of the Forest Keeper
     */
    public ForestKeeper(int hitPoints, int intrinsicWeaponDamage, float damageMultiplier, String intrinsicWeaponVerb, int intrinsicWeaponHitRate) {
        super("Forest Keeper", '8', hitPoints);
        this.damageMultiplier = damageMultiplier;
        this.intrinsicWeaponDamage = intrinsicWeaponDamage;
        this.intrinsicWeaponVerb = intrinsicWeaponVerb;
        this.intrinsicWeaponHitRate = intrinsicWeaponHitRate;

        this.setDefaultBehaviours();
    }

    /**
     * This method adds the default behaviours of the Forest Keeper to the map of behaviours.
     * <p>
     *     The default behaviour of the Forest Keeper is to wander around the map and attack the player if in proximity.
     *     The Forest Keeper will also follow the player if in proximity.
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
     * <p>Forest Keeper has 20% chance of dropping a healing vile when defeated.</p>
     * <p>Forest Keeper will drop 50 Runes when defeated</p>
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

        currentLocation.addItem(new Runes(50));
        returnStr += "\n" + this.name + " dropped 50 Runes";

        return returnStr;
    }

    /**
     * This method returns a new instance of Forest Keeper with the same attributes as this instance.
     *
     * <p>This method can be used by spawners to get copies of this Forest Keeper.</p>
     *
     * @return a new instance of Forest Keeper
     */
    @Override
    public Enemy getNewInstance() {
        return new ForestKeeper(
                this.getAttributeMaximum(BaseActorAttributes.HEALTH),
                this.intrinsicWeaponDamage,
                this.damageMultiplier,
                this.intrinsicWeaponVerb,
                this.intrinsicWeaponHitRate
        );
    }
}
