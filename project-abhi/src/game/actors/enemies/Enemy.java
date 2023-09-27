package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.capabilities.Status;
import game.behaviours.WanderBehaviour;
import java.util.HashMap;
import java.util.Map;

/**
 * The abstract class for all enemies in the game.
 * <p>
 *     This class extends Actor and implements Behaviour.
 * </p>
 * <p>
 *     This class also contains a map of behaviours that will be used by the enemy.
 * </p>
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public abstract class Enemy extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * The intrinsic weapon damage for the enemy
     */
    protected int intrinsicWeaponDamage;

    /**
     * The damage multiplier for the enemy
     */
    protected float damageMultiplier;

    /**
     * The intrinsic weapon verb for the enemy
     */
    protected String intrinsicWeaponVerb;

    /**
     * The intrinsic weapon hit rate for the enemy
     */
    protected int intrinsicWeaponHitRate;

    /**
     * The constructor for Enemy
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Returns the map of behaviours of this Enemy
     *
     * @return the map of behaviours of this Enemy
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     *  Returns a list of allowable actions to perform on the other Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * This method returns a new instance of this enemy with the same attributes.
     *
     * @return a new instance of this enemy
     */
    public abstract Enemy getNewInstance();


}
