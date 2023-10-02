/**
 * A class representing an action for performing a Stab and Move attack with a skill weapon.
 * This action extends the AttackAction class and allows for special behavior when a skill weapon's skill is active.
 */
package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.WanderBehaviour;
import game.items.skillweapons.weapons.SkillWeapon;

public class StabAndMoveAttackAction extends AttackAction {

    private SkillWeapon skillWeapon;

    /**
     * Constructor for the StabAndMoveAttackAction class when the skill weapon is provided.
     *
     * @param target      The target actor of the attack.
     * @param direction   The direction in which the attack is performed.
     * @param skillWeapon The skill weapon used to perform the attack.
     */
    public StabAndMoveAttackAction(Actor target, String direction, SkillWeapon skillWeapon) {
        super(target, direction, skillWeapon);
        this.skillWeapon = skillWeapon;
    }

    /**
     * Constructor for the StabAndMoveAttackAction class when only the target actor and direction are provided.
     *
     * @param target    The target actor of the attack.
     * @param direction The direction in which the attack is performed.
     */
    public StabAndMoveAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    /**
     * Executes the Stab and Move attack action, applying special behavior when the skill weapon's skill is active.
     *
     * @param actor The actor performing the attack.
     * @param map   The game map on which the attack is executed.
     * @return A string describing the result of the attack.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String attackActionResult = super.execute(actor, map);
        boolean didActionMiss = attackActionResult.contains("misses");

        // Move to a safe location if the skill is active and the attack did not miss
        if (skillWeapon.getWeaponSkill().isActive && !didActionMiss) {
            Display d = new Display();
            Behaviour behaviour = new WanderBehaviour();
            String moveActionResult = behaviour.getAction(actor, map).execute(actor, map);
            String deactivationResult = skillWeapon.getWeaponSkill().deactivate();
            d.println(deactivationResult);

            return attackActionResult + " And " + moveActionResult;
        }

        return attackActionResult;
    }
}
