package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action that allows the player to travel to a new map.
 *
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public class TravelToNewMapAction extends Action {

    /**
     * The map to travel to.
     */
    private GameMap nextMap;


    /**
     * Constructor.
     *
     * @param nextMap the map to travel to
     */
    public TravelToNewMapAction(GameMap nextMap) {
        this.nextMap = nextMap;
    }

    /**
     * Travel to the next map.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        int x = nextMap.getXRange().max()-1;
        int y = nextMap.getYRange().max();
        nextMap.addActor(actor, nextMap.at(x, y));
        return actor + " travels to the next map.";
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string containing the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to the next map.";
    }
}
