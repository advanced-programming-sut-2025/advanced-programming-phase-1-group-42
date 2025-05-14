package org.example.controllers;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.enums.GameMenuCommands;
import org.example.models.enums.Menu;
import org.example.models.game_structure.Gift;
import org.example.models.game_structure.*;
import org.example.models.enums.*;
import org.example.models.goods.*;
import org.example.models.interactions.Player;

import java.util.*;


public class TradeMenuController {

    //TODO: Parsa
    // Trading methods

    public Result tradeWithMoney(String receiver, String tradeType, String item,
                                 String amount, String price) {

        System.out.println(tradeType + " " + amount + " " + item + " " + "For" + price + "g from" + receiver );

        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Player receiverPlayer = App.getCurrentGame().findPlayer(receiver);
        if(receiverPlayer == null) {
            return new Result(false, "Player not found!");
        }

        int amountInt = Integer.parseInt(amount);
        int priceInt = Integer.parseInt(price);
        TradeType type = TradeType.valueOf(tradeType);

        if(type == TradeType.OFFER) {
        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(item);
        if(goods == null)
            return new Result(false, "Your don't have " + item + " in your inventory!");
        if(goods.size() < Integer.parseInt(amount))
            return new Result(false, "Your don't have enough number of " + item + " in your inventory!");
        }
        if(type == TradeType.REQUEST) {
            if(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() < priceInt)
                return new Result(false, "Your don't enough Money in your wallet!");
        }

        Trade trade = TradeManager.createTrade(currentPlayer, receiverPlayer, type, item, amountInt, priceInt, null , null );
        return new Result(true, "Trade created with ID: " + trade.getId());
    }

    public Result tradeError() {
        return new Result(true, "Can't trade with both money and item!");
    }

    public Result tradeWithGoods(String receiver, String tradeType, String item,
                                 String amount, String targetItem, String targetAmount) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Player receiverPlayer = App.getCurrentGame().findPlayer(receiver);
        TradeType type = TradeType.valueOf(tradeType);

        int amountInt = Integer.parseInt(amount);
        int targetAmountInt = Integer.parseInt(targetAmount);

        if(type == TradeType.OFFER) {
            ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(item);
            if(goods == null)
                return new Result(false, "Your don't have " + item + " in your inventory!");
            if(goods.size() < Integer.parseInt(amount))
                return new Result(false, "Your don't have enough number of " + item + " in your inventory!");
        }
        if(type == TradeType.REQUEST) {
            ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(targetItem);
            if(goods == null)
                return new Result(false, "Your don't have " + targetItem + " in your inventory!");
            if(goods.size() < Integer.parseInt(targetAmount))
                return new Result(false, "Your don't have enough number of " + targetItem + " in your inventory!");
        }

        Trade trade = TradeManager.createTrade(currentPlayer, receiverPlayer, type, item, amountInt, null, targetItem , targetAmountInt );
        return new Result(true, "Trade created with ID: " + trade.getId());

    }

    public Result tradeList() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        List<Trade> trades = TradeManager.getTradesFor(currentPlayer);

        if (trades.isEmpty()) {
            return new Result(true, "No trades found.");
        }
        for (Trade trade : trades) {
            System.out.println(trade);
        }

        return new Result(true, "");
    }

    public Result tradeResponse(String response, String tradeID) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();

        int tradeIdInt = Integer.parseInt(tradeID);

        boolean accept = false;
        if(response.equals("–accept")){
            accept = true;
        } else if (response.equals("–reject")){
            accept = false;
        }

        boolean realResponse = TradeManager.respondToTrade(currentPlayer, tradeIdInt, accept);

        return new Result(true, realResponse ? "Trade accepted." : "Trade rejected.");
    }

    public Result tradeHistory() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        List<Trade> history = currentPlayer.getTradeHistory();

        if (history.isEmpty()) {
            return new Result(true, "No trades found.");
        }
        for (Trade trade : history) {
            System.out.println(trade);

        }
        return new Result(true, "");
    }

    public Result exitTradeMenu() {
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You are now exiting trade");
    }

}
