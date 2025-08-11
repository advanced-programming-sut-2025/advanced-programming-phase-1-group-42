package com.StardewValley.server.controllers;

import com.StardewValley.models.Result;
import com.StardewValley.models.enums.Menu;
import com.StardewValley.models.game_structure.Trade;
import com.StardewValley.models.game_structure.TradeManager;
import com.StardewValley.models.game_structure.TradeType;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;

import java.util.ArrayList;
import java.util.List;


public class TradeMenuController {

    // Trading methods

    public Result tradeWithMoney(String receiver, String tradeType, String item,
                                 String amount, String price, ClientHandler clientHandler) {
            System.out.println(tradeType + " " + amount + " " + item + " " + "for " + price + "g from " + receiver );

        Player currentPlayer = clientHandler.getClientPlayer();
        Player receiverPlayer = clientHandler.getClientGame().findPlayer(receiver);
        if(receiverPlayer == null) {
            return new Result(false, "Player not found!");
        }

        int amountInt = Integer.parseInt(amount);
        int priceInt = Integer.parseInt(price);
        TradeType type = TradeType.valueOf(tradeType);

        if(type == TradeType.OFFER) {
            ArrayList<Good> goods = clientHandler.getClientPlayer().getInventory().isInInventory(item);
            if(goods == null)
                return new Result(false, "Your don't have " + item + " in your inventory!");
            if(goods.size() < Integer.parseInt(amount))
                return new Result(false, "Your don't have enough number of " + item + " in your inventory!");
        }
        if(type == TradeType.REQUEST) {
            if(clientHandler.getClientPlayer().getWallet().getBalance() < priceInt)
                return new Result(false, "Your don't enough Money in your wallet!");
        }

        Trade trade = TradeManager.createTrade(currentPlayer, receiverPlayer, type, item, amountInt, priceInt,
            null , null, clientHandler);
        clientHandler.getClientGame().getAllTrades().add(trade);
        return new Result(true, "Trade created with ID: " + trade.getId());
    }

    public Result tradeError() {
        return new Result(true, "Can't trade with both money and item!");
    }

    public Result tradeWithGoods(String receiver, String tradeType, String item,
                                 String amount, String targetItem, String targetAmount, ClientHandler clientHandler) {
        Player currentPlayer = clientHandler.getClientPlayer();
        Player receiverPlayer = clientHandler.getClientGame().findPlayer(receiver);
        TradeType type = TradeType.valueOf(tradeType);

        int amountInt = Integer.parseInt(amount);
        int targetAmountInt = Integer.parseInt(targetAmount);

        if(type == TradeType.OFFER) {
            ArrayList<Good> goods = clientHandler.getClientPlayer().getInventory().isInInventory(item);
            if(goods == null)
                return new Result(false, "Your don't have " + item + " in your inventory!");
            if(goods.size() < Integer.parseInt(amount))
                return new Result(false, "Your don't have enough number of " + item + " in your inventory!");
        }
        if(type == TradeType.REQUEST) {
            ArrayList<Good> goods = clientHandler.getClientPlayer().getInventory().isInInventory(targetItem);
            if(goods == null)
                return new Result(false, "Your don't have " + targetItem + " in your inventory!");
            if(goods.size() < Integer.parseInt(targetAmount))
                return new Result(false, "Your don't have enough number of " + targetItem + " in your inventory!");
        }

        Trade trade = TradeManager.createTrade(currentPlayer, receiverPlayer, type, item, amountInt,
            null, targetItem , targetAmountInt, clientHandler);
        clientHandler.getClientGame().getAllTrades().add(trade);
        return new Result(true, "Trade created with ID: " + trade.getId());
    }

    public Result tradeList(ClientHandler clientHandler) {
        Player currentPlayer = clientHandler.getClientPlayer();
        List<Trade> trades = TradeManager.getTradesFor(currentPlayer, clientHandler);

        if (trades.isEmpty()) {
            return new Result(true, "No trades found.");
        }
        for (Trade trade : trades) {
            System.out.println(trade);
        }

        return new Result(true, "");
    }

    public Result tradeResponse(String response, String tradeID, ClientHandler clientHandler) {
        Player currentPlayer = clientHandler.getClientPlayer();

        int tradeIdInt = Integer.parseInt(tradeID);

        boolean accept = false;
        if(response.equals("–accept")){
            accept = true;
        } else if (response.equals("–reject")){
            accept = false;
        }

        return TradeManager.respondToTrade(currentPlayer, tradeIdInt, accept, clientHandler);
    }

    public Result tradeHistory(ClientHandler clientHandler) {
        Player currentPlayer = clientHandler.getClientPlayer();
        List<Trade> history = currentPlayer.getTradeHistory();

        if (history.isEmpty()) {
            return new Result(true, "No trades found.");
        }
        for (Trade trade : history) {
            System.out.println(trade);

        }
        return new Result(true, "");
    }

    public Result exitTradeMenu(ClientHandler clientHandler) {
//        AppClient.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You are now exiting trade\n");
    }

}
