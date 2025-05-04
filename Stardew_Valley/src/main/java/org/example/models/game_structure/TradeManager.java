package org.example.models.game_structure;

import java.util.*;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.goods.Good;
import org.example.models.interactions.*;

public class TradeManager {
    private static final List<Trade> allTrades = new ArrayList<>();

    public static Trade createTrade(Player sender, Player receiver, TradeType type,
                                    String item, int amount, Integer price,
                                    String targetItem, Integer targetAmount) {
        Trade trade = new Trade(type, sender, receiver, item, amount, price, targetItem, targetAmount);
        allTrades.add(trade);
        sender.addSentTrade(trade);
        receiver.addReceivedTrade(trade);
        return trade;
    }

    public static List<Trade> getTradesFor(Player player) {
        List<Trade> list = new ArrayList<>();
        for (Trade trade : allTrades) {
            if (trade.getReceiver().equals(player) && !trade.isAccepted()) {
                list.add(trade);
            }
        }
        return list;
    }

    public static void respondToTrade(Player player, int tradeId, boolean accept) {

        for (Trade trade : allTrades) {
            if (trade.getId() == tradeId && trade.getReceiver().equals(player)) {
                if (accept) {
                    App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                            (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                    trade.getSender().getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                            (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                    if (trade.getTradePrice() != null) {
                        if (trade.getType() == TradeType.OFFER) {

                            trade.getSender().getWallet().increaseBalance(trade.getAmount());
                            trade.getReceiver().getWallet().decreaseBalance(trade.getAmount());
                            trade.getReceiver().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())), trade.getAmount());
                            trade.getSender().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());

                        }
                        if (trade.getType() == TradeType.REQUEST) {

                            trade.getReceiver().getWallet().increaseBalance(trade.getAmount());
                            trade.getSender().getWallet().decreaseBalance(trade.getAmount());
                            trade.getSender().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())), trade.getAmount());
                            trade.getReceiver().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());
                        }
                    } else {
                        if (trade.getType() == TradeType.OFFER) {

                            trade.getReceiver().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())), trade.getAmount());
                            trade.getSender().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());
                            trade.getReceiver().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getTargetItem())), trade.getTargetAmount());
                            trade.getSender().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getTargetItem()), trade.getTargetAmount());
                        }
                        if (trade.getType() == TradeType.REQUEST) {

                            trade.getSender().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())), trade.getAmount());
                            trade.getReceiver().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());
                            trade.getSender().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getTargetItem())), trade.getTargetAmount());
                            trade.getReceiver().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getTargetItem()), trade.getTargetAmount());
                        }
                    }

                }
                else{
                    App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                            (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                    trade.getSender().getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                            (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                }
                trade.setAccepted(accept);
                return;
            }
        }
    }
}
