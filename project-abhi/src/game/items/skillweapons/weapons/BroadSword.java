package game.items.skillweapons.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateWeaponSkillAction;
import game.actions.AttackAction;
import game.capabilities.Status;
import game.items.skillweapons.skilltypes.Focus;
import game.items.skillweapons.skilltypes.WeaponSkill;
import game.items.trade.TradeItem;
import game.items.trade.Tradeable;

/**
 * A class representing a Broadsword that can be used to attack other actors.
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class BroadSword extends SkillWeapon implements Tradeable {

    /**
     * Constructor for BroadSword.
     */
    public BroadSword() {
        super("Broadsword", '1', 110, "Slashes", 80);
        this.setWeaponSkill(new Focus(this, 10, 80));
    }

    /**
     * Constructor for BroadSword that takes in damage and hit rate.
     *
     * @param damage the damage stat of the weapon
     * @param hitRate the hit rate of the weapon
     */
    public BroadSword(int damage, int hitRate) {
        super("Broadsword", '1', damage, "Slashes", hitRate);
    }

    /**
     * List of allowable actions that the owner can perform on this weapon.
     *
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);
        WeaponSkill skill = this.getWeaponSkill();

        if (skill != null && skill.canActivate(owner)) {
            actions.add(new ActivateWeaponSkillAction(this));
        }
        return actions;
    }

    /**
     * List of allowable actions that the weapon allows its owner do to other actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);

        if (otherActor.hasCapability(Status.TRADER)) {
            actions.add(new TradeItem(this, this.getValue()).getSellAction(otherActor));
        } else {
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }

        return actions;
    }

    /**
     * This method is called every turn by the engine if this item is in the inventory of an actor.
     * <p>This method calls the tick method of the skill of this weapon to keep track of the skill's active duration.</p>
     *
     * @param currentLocation The location of the actor carrying this weapon.
     * @param actor The actor carrying this weapon.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        WeaponSkill skill = this.getWeaponSkill();
        if (skill != null) {
            skill.tick();
        }
    }


    /**
     * This method returns the resale value of the item
     *
     * @return the resale value of the item
     */
    @Override
    public int getValue() {
        return 100;
    }

    /**
     * Method to execute the logic of buying an item
     *
     * @param seller the actor selling the item
     * @param buyer  the actor buying the item
     * @param price  the price at which the item is being bought
     * @return a string describing the result of the transaction
     */
    @Override
    public String buy(Actor seller, Actor buyer, int price) {
        String output = "";

        if (buyer.getBalance() < price) {
            output += buyer + " does not have enough runes to buy " + this + " from " + seller + ".";
        } else {
            buyer.deductBalance(price);
            seller.addBalance(price);
            if (Math.random() <= 0.05) {
                output += seller + " refused to give " + this + " to " + buyer + ".";
            } else {
                buyer.addItemToInventory(new BroadSword());
                output += buyer + " bought " + this + " from " + seller + " for " + price + " runes.";
            }
        }

        return output;
    }

    /**
     * Method to execute the logic of selling an item
     *
     * @param seller the actor selling the item
     * @param buyer  the actor buying the item
     * @param price  the price at which the item is being sold
     * @return a string describing the result of the transaction
     */
    @Override
    public String sell(Actor seller, Actor buyer, int price) {
        seller.removeItemFromInventory(this);
        seller.addBalance(price);
        buyer.deductBalance(price);

        return seller + " sold " + this + " to " + buyer + " for " + price + " runes.";
    }
}
