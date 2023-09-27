package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.capabilities.Ability;

/**
 * Class representing an Old Key
 *
 * <p>Old Key can be used to open doors.</p>
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class OldKey extends Item {

    /***
     * Constructor for OldKey
     */
    public OldKey() {
        super("Old Key", 'k', true);
        this.addCapability(Ability.CAN_UNLOCK_DOORS);
    }
}
