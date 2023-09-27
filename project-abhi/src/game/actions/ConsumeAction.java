package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumables.ConsumableItem;

/**
 * A class representing the action of consuming an item.
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class ConsumeAction extends Action {

    /**
     * The item to be consumed.
     */
    private ConsumableItem consumable;

    /**
     * Constructor for ConsumeAction
     *
     * @param consumable the item to be consumed
     */
    public ConsumeAction(ConsumableItem consumable) {
        this.consumable = consumable;
    }

    /**
     * Consume the item and apply its effects.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String returnStr = this.consumable.consume(actor);
        return returnStr;
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string containing the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.consumable;
    }
}
