package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.behaviours.WanderBehaviour;
import game.items.skillweapons.weapons.SkillWeapon;

public class GreatSlamAttackAction extends AttackAction{


    SkillWeapon skillWeapon;

    Actor target;
    public GreatSlamAttackAction(Actor target, String direction, SkillWeapon skillWeapon) {
        super(target, direction, skillWeapon);
        this.skillWeapon = skillWeapon;
        this.target = target;
    }

    public GreatSlamAttackAction(Actor target, String direction) {
        super(target, direction);
    }


    @Override
    public String execute(Actor actor, GameMap map) {

        Location targetLocationBeforePotentialDeath = map.locationOf(target);
        String attackActionResult = super.execute(actor,map);
        boolean didActionMiss = attackActionResult.contains("misses");
        //inflict damage to nearby players if skill active

        if(skillWeapon.getWeaponSkill().isActive && !didActionMiss)
        {

            skillWeapon.updateDamageMultiplier(0.5f);

            //original hit rate
            int originalHitRate = skillWeapon.chanceToHit();
            skillWeapon.updateHitRate(100);
            Display d = new Display();


            for (Exit exit : targetLocationBeforePotentialDeath.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    Actor nearbyTarget = destination.getActor();
                    AttackAction attack = new AttackAction(nearbyTarget,destination.toString(),skillWeapon);

                    String spreadAttackResult = attack.execute(actor,map);
                    d.println(spreadAttackResult);
                }
            }
            String deactivationResult = skillWeapon.getWeaponSkill().deactivate();
            d.println(deactivationResult);

            //reset weapon damage and hit rate
            skillWeapon.updateDamageMultiplier(1.0f);
            skillWeapon.updateHitRate(originalHitRate);

        }



        return attackActionResult;

    }


}
