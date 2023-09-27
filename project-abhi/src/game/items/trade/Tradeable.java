package game.items.trade;


import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for items that can be traded
 *
 * Created by:
 * @author Abhijit Upadhyay
 */
public interface Tradeable {

    /**
     * This method returns the resale value of the item
     *
     * @return the resale value of the item
     */
    int getValue();

    /**
     * Method to execute the logic of buying an item
     *
     * @param seller the actor selling the item
     * @param buyer the actor buying the item
     * @param price the price at which the item is being bought
     * @return a string describing the result of the transaction
     */
    String buy(Actor seller, Actor buyer, int price);

    /**
     * Method to execute the logic of selling an item
     *
     * @param seller the actor selling the item
     * @param buyer the actor buying the item
     * @param price the price at which the item is being sold
     * @return a string describing the result of the transaction
     */
    String sell(Actor seller, Actor buyer, int price);

}
