package com.StardewValley.models.game_structure;

import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;

import java.util.ArrayList;
import java.util.List;

public class TradeManager {
    public static Trade createTrade(Player sender, Player receiver, TradeType type,
                                    String item, int amount, Integer price,
                                    String targetItem, Integer targetAmount, ClientHandler clientHandler) {
        Trade trade = new Trade(type, sender, receiver, item, amount, price, targetItem, targetAmount);
        clientHandler.getClientGame().getAllTrades().add(trade);
        sender.addSentTrade(trade);
        receiver.addReceivedTrade(trade);
        return trade;
    }

    public static List<Trade> getTradesFor(Player player, ClientHandler clientHandler) {
        List<Trade> list = new ArrayList<>();
        for (Trade trade : clientHandler.getClientGame().getAllTrades()) {
            if (trade.getReceiver().equals(player) && !trade.isAccepted()) {
                list.add(trade);
            }
        }
        return list;
    }

    public static Result respondToTrade(Player player, int tradeId, boolean accept, ClientHandler clientHandler) {
        for (Trade trade : clientHandler.getClientGame().getAllTrades()) {
            if (trade.getId() == tradeId && trade.getReceiver().equals(player)) {
                if (accept) {
                    if (trade.getTradePrice() != null) {
                        if (trade.getType() == TradeType.OFFER) {
                            if (trade.getAmount() > trade.getReceiver().getWallet().getBalance()) {
                                System.out.println("You dont have enough money!, Dumbass");
                                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(trade.getSender().getUsername(),

                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.getSender().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.setAccepted(false);
                                trade.setResponded(true);
                                return new Result(false ,"You dont have enough money!, Dumbass" );
                            } else {
                                trade.getSender().getWallet().increaseBalance(trade.getAmount());
                                trade.getSender().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());

                                trade.getReceiver().getWallet().decreaseBalance(trade.getAmount());
                                trade.getReceiver().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())),
                                    trade.getAmount(), clientHandler.getClientGame(), clientHandler.getClientPlayer());

                                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(trade.getSender().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.getSender().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.setAccepted(true);
                                trade.setResponded(true);
                                return new Result(true ,"Trade Successful" );
                            }
                        }
                        if (trade.getType() == TradeType.REQUEST) {
                            if (trade.getReceiver().getInventory().howManyInInventoryByType(Good.newGoodType(trade.getItem())) < trade.getAmount()) {
                                System.out.println("You dont have enough Material!, Dumbass");
                                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(trade.getSender().getUsername(),

                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.getSender().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.setAccepted(false);
                                trade.setResponded(true);
                                return new Result(false ,"You dont have enough Material!, Dumbass" );
                            } else {

                                trade.getReceiver().getWallet().increaseBalance(trade.getAmount());
                                trade.getReceiver().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());
                                trade.getSender().getWallet().decreaseBalance(trade.getAmount());
                                trade.getSender().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())), trade.getAmount(),
                                    clientHandler.getClientGame(), clientHandler.getClientPlayer());

                                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(trade.getSender().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.getSender().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.setAccepted(true);
                                trade.setResponded(true);
                                return new Result(true ,"Trade Successful" );
                            }
                        }
                    } else {
                        if (trade.getType() == TradeType.OFFER) {
                            if (trade.getReceiver().getInventory().howManyInInventoryByType(Good.newGoodType(trade.getItem())) < trade.getAmount()) {
                                System.out.println("You dont have enough Material!, Dumbass");
                                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(trade.getSender().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.getSender().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.setAccepted(false);
                                trade.setResponded(true);
                                return new Result(false ,"You dont have enough Material!, Dumbass" );
                            } else {
                                trade.getReceiver().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())), trade.getAmount(),
                                    clientHandler.getClientGame(), clientHandler.getClientPlayer());
                                trade.getReceiver().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getTargetItem()), trade.getTargetAmount());
                                trade.getSender().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());
                                trade.getSender().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getTargetItem())), trade.getTargetAmount(),
                                    clientHandler.getClientGame(), clientHandler.getClientPlayer());

                                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(trade.getSender().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.getSender().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.setAccepted(true);
                                trade.setResponded(true);
                                return new Result(true ,"Trade Successful" );
                            }
                        }
                        if (trade.getType() == TradeType.REQUEST) {
                            if (trade.getReceiver().getInventory().howManyInInventoryByType(Good.newGoodType(trade.getItem())) < trade.getAmount()) {
                                System.out.println("You dont have enough Material!, Dumbass");
                                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(trade.getSender().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.getSender().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                                trade.setAccepted(false);
                                trade.setResponded(true);
                                return new Result(false ,"You dont have enough Material!, Dumbass" );
                            } else {
                                trade.getSender().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getItem())), trade.getAmount(),
                                    clientHandler.getClientGame(), clientHandler.getClientPlayer());
                                trade.getSender().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getTargetItem()), trade.getTargetAmount());
                                trade.getReceiver().getInventory().removeItemsFromInventory(Good.newGoodType(trade.getItem()), trade.getAmount());
                                trade.getReceiver().getInventory().addGood(Good.newGood(Good.newGoodType(trade.getTargetItem())), trade.getTargetAmount(),
                                    clientHandler.getClientGame(), clientHandler.getClientPlayer());

                                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(trade.getSender().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.getSender().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 30));
                                trade.setAccepted(true);
                                trade.setResponded(true);
                                return new Result(true ,"Trade Successful" );
                            }
                        }
                    }
                } else {
                    clientHandler.getClientPlayer().getFriendShips().computeIfPresent(trade.getSender().getUsername(),
                            (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                    trade.getSender().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                            (k, pair) -> new Pair<>(pair.first(), pair.second() - 30));
                    trade.setAccepted(false);
                    trade.setResponded(true);
                    return new Result(false ,"Trade Rejected" );
                }

            }
        }
        return new Result(false ,"Trade Rejected" );
    }
}
