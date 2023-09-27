package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;
import game.items.consumables.HealingVial;
import game.items.consumables.RefreshingFlask;
import game.items.skillweapons.weapons.BroadSword;
import game.items.skillweapons.weapons.GiantHammer;
import game.items.skillweapons.weapons.GreatKnife;
import game.items.trade.TradeItem;
import java.util.ArrayList;

public class Traveller extends Actor {

    ArrayList<TradeItem> inventory = new ArrayList<>();

    public Traveller() {
        super("Isolated Traveller", 'ඞ', 100);
        this.addCapability(Status.TRADER);
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.add(new TradeItem(new HealingVial(), 100));
        inventory.add(new TradeItem(new RefreshingFlask(), 75));
        inventory.add(new TradeItem(new BroadSword(), 250));
        inventory.add(new TradeItem(new GreatKnife(), 300));
        //inventory.add(new TradeItem(new GiantHammer(), 300));
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns a collection of BuyActions for all items being sold by this Traveller
     *
     * @param otherActor the Actor that might be buying from this Traveller
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of the Actions that the other Actor can perform
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            for (TradeItem item : inventory) {
                actions.add(item.getBuyAction(this));
            }
        }

        return actions;
    }
}
