package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.WanderBehaviour;
import game.items.skillweapons.weapons.SkillWeapon;

public class StabAndMoveAttackAction extends AttackAction{


    private SkillWeapon skillWeapon;

    public StabAndMoveAttackAction(Actor target, String direction, SkillWeapon skillWeapon) {
        super(target, direction, skillWeapon);//use weapon to get the knife and see if skill is active or not
        this.skillWeapon = skillWeapon;
    }

    public StabAndMoveAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        String attackActionResult = super.execute(actor,map);
        boolean didActionMiss = attackActionResult.contains("misses");
        //move to safe location if skill activated
        if(skillWeapon.getWeaponSkill().isActive && !didActionMiss)
        {

            Display d = new Display();
            Behaviour behaviour = new WanderBehaviour();
            String moveActionResult = behaviour.getAction(actor,map).execute(actor,map);
            String deactivationResult = skillWeapon.getWeaponSkill().deactivate();
            d.println(deactivationResult);

            return attackActionResult + " And "  + moveActionResult;
        }


        return attackActionResult;

    }



}
