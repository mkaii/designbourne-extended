package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.FancyMessage;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Abhijit Upadhyay
 *
 */
public class Player extends Actor {
    /**
     * The intrinsic weapon damage for the player
     */
    private static int intrinsicWeaponDamage = 15;

    /**
     * The damage multiplier for the player
     */
    private float damageMultiplier = 1.0f;

    /**
     * The intrinsic weapon verb for the player
     */
    private String intrinsicWeaponVerb = "Jabs";

    /**
     * Constructor for the Player.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.CAN_ENTER_FLOORS);

        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(200));
    }

    /**
     * Constructor for the Player that takes in a damage multiplier and damage for the intrinsic weapon.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     * @param damageMultiplier the damage multiplier for the player
     * @param intrinsicWeaponDamage the damage for the intrinsic weapon of the player
     */
    public Player(String name, char displayChar, int hitPoints, float damageMultiplier, int intrinsicWeaponDamage) {
        super(name, displayChar, hitPoints);
        this.damageMultiplier = damageMultiplier;
        this.intrinsicWeaponDamage = intrinsicWeaponDamage;

        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.CAN_ENTER_FLOORS);

        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(200));
    }

    /**
     * Method to play a turn.
     * <p>Prints the player's name, HP, and stamina.</p>
     * <p>Handles multi-turn Actions.</p>
     * <p>Displays the console menu.</p>
     *
     * @param actions   collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn, if the action needs to be followed up then the method returns the follow-up Action.
     * @param map       the map containing the Actor
     * @param display   the I/O object to which messages may be written
     *
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.roundStartRegen();

        Display d = new Display();
        d.println(this.name);
        d.println("HP: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH));
        d.println("STAMINA: " + this.getAttribute(BaseActorAttributes.STAMINA) + "/" + this.getAttributeMaximum(BaseActorAttributes.STAMINA));

        d.println("WALLET: $" + this.getBalance());

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Method to regenerate 1% stamina at the start of the every round.
     */
    private void roundStartRegen() {
        int maxStamina = this.getAttributeMaximum(BaseActorAttributes.STAMINA);
        this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, maxStamina/100);
    }

    /**
     * Creates and returns an intrinsic weapon.
     * <p>There is 20% chance that the attack will miss.</p>
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int damage = Math.round(intrinsicWeaponDamage * this.damageMultiplier);;

        return new IntrinsicWeapon(damage, intrinsicWeaponVerb, 80); // 80% chance to hit (20% chance to miss)
    }

    /**
     * Method that is executed when the player dies due to natural causes or accident
     *
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(GameMap map) {
        map.removeActor(this);
        return FancyMessage.YOU_DIED;
    }

    /**
     * Method that is executed when the player dies due to the action of another actor
     *
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        String returnStr = super.unconscious(actor, map);
        returnStr += "\n" + FancyMessage.YOU_DIED;
        return returnStr;
    }
}
