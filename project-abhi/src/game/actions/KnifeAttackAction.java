package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.behaviours.WanderBehaviour;
import game.items.skillweapons.weapons.SkillWeapon;

public class KnifeAttackAction extends AttackAction{


    private SkillWeapon greatKnife;

    public KnifeAttackAction(Actor target, String direction, SkillWeapon skillWeapon) {
        super(target, direction, skillWeapon);//use weapon to get the knife and see if skill is active or not
        greatKnife = skillWeapon;
    }

    public KnifeAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        String attackActionResult = super.execute(actor,map);

        //move to safe location if skill activated
        if(greatKnife.getWeaponSkill().isActive)
        {
            Behaviour behaviour = new WanderBehaviour();
            String moveActionResult = behaviour.getAction(actor,map).execute(actor,map);
            return attackActionResult + " And "  + moveActionResult; 
        }


        return attackActionResult;

    }



}
