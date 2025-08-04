package com.StardewValley.models.game_structure;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.interactions.Player;

import java.util.ArrayList;
import java.util.List;

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

    public static boolean respondToTrade(Player player, int tradeId, boolean accept) {

        for (Trade trade : allTrades) {
            if (trade.getId() == tradeId && trade.getReceiver().equals(player)) {
                if (accept) {
                    if (trade.getTradePrice() != null) {
                        if (trade.getType() == TradeType.OFFER) {
                            if (trade.getAmount() > trade.getReceiver().getWallet().getBalance()) {
                                System.out.println("You dont have enough money!, Dumbass");
                                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.getSender().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.setAccepted(false);
                                return false;
                            } else {
                                trade.getSender().getWallet().increaseBalance(trade.getAmount());
                                trade.getReceiver().getWallet().decreaseBalance(trade.getAmount());

                                GoodType goodType = Good.newGoodType(trade.getItem());
                                ArrayList<Good> newGoods = Good.newGoods(goodType, trade.getAmount());
                                trade.getReceiver().getInventory().addGood(newGoods);

                                trade.getSender().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());
                                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.getSender().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.setAccepted(true);
                                return true;
                            }
                        }
                        if (trade.getType() == TradeType.REQUEST) {
                            if (trade.getReceiver().getInventory().howManyInInventoryByType(Good.newGoodType(trade.getItem())) < trade.getAmount()) {
                                System.out.println("You dont have enough Material!, Dumbass");
                                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.getSender().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.setAccepted(false);
                                return false;
                            } else {

                                trade.getReceiver().getWallet().increaseBalance(trade.getAmount());
                                trade.getSender().getWallet().decreaseBalance(trade.getAmount());

                                GoodType goodType = Good.newGoodType(trade.getItem());
                                ArrayList<Good> newGoods = Good.newGoods(goodType, trade.getAmount());
                                trade.getSender().getInventory().addGood(newGoods);

                                trade.getReceiver().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());

                                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.getSender().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.setAccepted(true);
                                return true;
                            }
                        }
                    } else {
                        if (trade.getType() == TradeType.OFFER) {
                            if (trade.getReceiver().getInventory().howManyInInventoryByType(Good.newGoodType(trade.getItem())) < trade.getAmount()) {
                                System.out.println("You dont have enough Material!, Dumbass");
                                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.getSender().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.setAccepted(false);
                                return false;
                            } else {
                                trade.getReceiver().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())), trade.getAmount());
                                trade.getSender().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());

                                GoodType goodType = Good.newGoodType(trade.getItem());
                                ArrayList<Good> newGoods = Good.newGoods(goodType, trade.getAmount());
                                trade.getSender().getInventory().addGood(newGoods);

                                trade.getReceiver().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getTargetItem()), trade.getTargetAmount());

                                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.getSender().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.setAccepted(true);
                                return true;
                            }
                        }
                        if (trade.getType() == TradeType.REQUEST) {
                            if (trade.getReceiver().getInventory().howManyInInventoryByType(Good.newGoodType(trade.getItem())) < trade.getAmount()) {
                                System.out.println("You dont have enough Material!, Dumbass");
                                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.getSender().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.setAccepted(false);
                                return false;
                            } else {
                                trade.getReceiver().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())), trade.getAmount());
                                trade.getSender().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());

                                GoodType goodType = Good.newGoodType(trade.getItem());
                                ArrayList<Good> newGoods = Good.newGoods(goodType, trade.getAmount());
                                trade.getSender().getInventory().addGood(newGoods);

                                trade.getReceiver().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getTargetItem()), trade.getTargetAmount());

                                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.getSender().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.setAccepted(true);
                                return true;
                            }
                        }
                    }
                } else {
                    AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.getSender(),
                            (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                    trade.getSender().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                            (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                    trade.setAccepted(false);
                    return false;
                }

            }
        }
        return false;
    }
}
