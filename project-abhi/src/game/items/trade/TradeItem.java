package game.items.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.BuyAction;
import game.actions.SellAction;

public class TradeItem {

    private Tradeable item;
    private int sellPrice;

    public TradeItem(Tradeable item, int sellPrice) {
        this.item = item;
        this.sellPrice = sellPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public String toString() {
        return item.toString();
    }

    public String buy(Actor seller, Actor buyer) {
        return item.buy(seller, buyer, sellPrice);
    }

    public String sell(Actor seller, Actor buyer) {
        return item.sell(seller, buyer, sellPrice);
    }

    public Action getBuyAction(Actor seller) {
        return new BuyAction(seller, this);
    }

    public Action getSellAction(Actor buyer) {
        return new SellAction(buyer, this);
    }

}
