package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class representing a Runes
 *
 * <p>Runes can be consumed by an actor to restore wallet.</p>
 *
 * Created by:
 * @author Lok Mei Hui
 */
public class Runes extends ConsumableItem{

    /**
     * The amount of runes to be restored upon consumption.
     */
    private int amount;
    /**
     * Constructor for Runes
     */
    public Runes() {
        super("Runes", '$', true);
    }

    /**
     * Constructor for Runes that takes in an amount of runes to be added to the wallet.
     *
     * <p>For example, new Runes(50)</p>
     *
     * @param amount the amount of runes.
     */
    public Runes(int amount) {
        super("Runes", '$', true);
        this.amount = amount;
    }

    /**
     * This method applies the effects of consumption of this item to the actor
     *
     * <p>Add an amount of runes to player's wallet.</p>
     *
     * @param actor the actor that consuming this item
     * @return a string describing the effects of consumption
     */
    @Override
    public String consume(Actor actor) {
        actor.addBalance(amount);

        actor.removeItemFromInventory(this);

        return actor + " consumes " + this ;
    }
}
