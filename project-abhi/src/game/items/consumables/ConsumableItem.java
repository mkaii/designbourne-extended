package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;

/**
 * Class representing a ConsumableItem
 *
 * <p>ConsumableItem can be consumed by an actor.</p>
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public abstract class ConsumableItem extends Item {

    /***
     * Constructor for ConsumableItem
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ConsumableItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * List of allowable actions that the owner can perform on this ConsumableItem.
     *
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Consumes the ConsumableItem.
     *
     * <p>Applies suitable effects to the consumer.</p>
     *
     * @param actor the actor that is consuming the ConsumableItem
     * @return a string describing the result of consuming the ConsumableItem
     */
    public abstract String consume(Actor actor);
}
