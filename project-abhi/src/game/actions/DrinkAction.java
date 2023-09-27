package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.drinkable.DrinkableGround;

/**
 * A class representing the action of drinking water from a ground
 *
 * Created by:
 * @author Lok Mei Hui
 */

public class DrinkAction extends Action {
    /**
     * The ground that is drinkable.
     */

    private DrinkableGround drinkable;

    /**
     * Constructor for DrinkAction
     *
     * @param drinkable the ground to be drink
     */
    public DrinkAction(DrinkableGround drinkable) {
        this.drinkable = drinkable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.drinkable.drink(actor);
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string containing the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from drinkable ground";
    }
}
