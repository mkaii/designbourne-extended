/**
 * A class representing an action for performing a Great Slam attack with a skill weapon.
 * This action extends the AttackAction class and allows for special behavior when a skill weapon's skill is active.
 */
package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.skillweapons.weapons.SkillWeapon;

public class GreatSlamAttackAction extends AttackAction {

    SkillWeapon skillWeapon;
    Actor target;

    /**
     * Constructor for the GreatSlamAttackAction class when the skill weapon and target actor are provided.
     *
     * @param target      The target actor of the attack.
     * @param direction   The direction in which the attack is performed.
     * @param skillWeapon The skill weapon used to perform the attack.
     */
    public GreatSlamAttackAction(Actor target, String direction, SkillWeapon skillWeapon) {
        super(target, direction, skillWeapon);
        this.skillWeapon = skillWeapon;
        this.target = target;
    }

    /**
     * Constructor for the GreatSlamAttackAction class when only the target actor and direction are provided.
     *
     * @param target    The target actor of the attack.
     * @param direction The direction in which the attack is performed.
     */
    public GreatSlamAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    /**
     * Executes the Great Slam attack action, applying special behavior when the skill weapon's skill is active.
     *
     * @param actor The actor performing the attack.
     * @param map   The game map on which the attack is executed.
     * @return A string describing the result of the attack.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location targetLocationBeforePotentialDeath = map.locationOf(target);
        String attackActionResult = super.execute(actor, map);
        boolean didActionMiss = attackActionResult.contains("misses");

        // Inflict damage to nearby players if the skill is active and the attack did not miss
        if (skillWeapon.getWeaponSkill().isActive && !didActionMiss) {
            skillWeapon.updateDamageMultiplier(0.5f);
            int originalHitRate = skillWeapon.chanceToHit();
            skillWeapon.updateHitRate(100);

            for (Exit exit : targetLocationBeforePotentialDeath.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    Actor nearbyTarget = destination.getActor();
                    AttackAction attack = new AttackAction(nearbyTarget, destination.toString(), skillWeapon);
                    attack.execute(actor, map); // Perform the attack on nearby actors
                }
            }

            String deactivationResult = skillWeapon.getWeaponSkill().deactivate();
            // Reset weapon damage and hit rate
            skillWeapon.updateDamageMultiplier(1.0f);
            skillWeapon.updateHitRate(originalHitRate);
        }

        return attackActionResult;
    }
}
