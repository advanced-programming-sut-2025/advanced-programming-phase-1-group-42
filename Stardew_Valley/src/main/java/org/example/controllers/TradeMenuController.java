package org.example.controllers;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.enums.GameMenuCommands;
import org.example.models.enums.Menu;
import org.example.models.game_structure.Gift;
import org.example.models.game_structure.Trade;
import org.example.models.goods.Good;
import org.example.models.goods.*;
import org.example.models.interactions.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TradeMenuController {

    //TODO: Parsa
    // Trading methods

    public Result tradeWithMoney(String receiver, String tradeType, String item,
                                 String amount, String Price) {
        //TODO
        Player player = App.getCurrentGame().findPlayer(receiver);
        if(player == null) {
            return new Result(false, "Player not found!");
        }

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(item);

        if(goods == null)
            return new Result(false, "Your don't have " + item + " in your inventory!");
        if(goods.size() < Integer.parseInt(amount))
            return new Result(false, "Your don't have enough number of " + item + " in your inventory!");
        if(!(Good.newGoodType(item) instanceof Good))
            return new Result(false, item + "is not a good!");

        int number = Integer.parseInt(amount);
        ArrayList<Good> tradeGoods = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            tradeGoods.add(goods.getLast());
            goods.removeLast();
        }

        Trade trade = new Trade(tradeGoods);
        player.getTradeList().add(new Pair<>(player, trade));
        player.getNews().add("A new trade offer has been added to your trade list from " + App.getCurrentGame().getCurrentPlayer().getUser().getUsername() + "!");

        return new Result(true, "Your trade proposal has been sent to " + receiver + "!");

    }

    public Result tradeError() {
        return new Result(true, "Can't trade with both money and item!");
    }
    public Result tradeWithGoods(String receiver, String tradeType, String item,
                                 String amount, String targetItem, String targetAmount) {
        Player player = App.getCurrentGame().findPlayer(receiver);
        if(player == null) {
            return new Result(false, "Player not found!");
        }

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(item);

        if(goods == null)
            return new Result(false, "Your don't have " + item + " in your inventory!");
        if(goods.size() < Integer.parseInt(amount))
            return new Result(false, "Your don't have enough number of " + item + " in your inventory!");
        if(!(Good.newGoodType(item) instanceof Good))
            return new Result(false, item + "is not a good!");

        int number = Integer.parseInt(amount);
        ArrayList<Good> tradeGoods = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            tradeGoods.add(goods.getLast());
            goods.removeLast();
        }

        Trade trade = new Trade(tradeGoods);
        player.getTradeList().add(new Pair<>(player, trade));
        player.getNews().add("A new trade offer has been added to your trade list from " + App.getCurrentGame().getCurrentPlayer().getUser().getUsername() + "!");

        return new Result(true, "Your trade proposal has been sent to " + receiver + "!");

    }

    public Result tradeList() {
        StringBuilder list = new StringBuilder();
        list.append("Trade List:\n");
        int ptr = 1;
        for (Pair<Player, Trade> playerTradePair : App.getCurrentGame().getCurrentPlayer().getTradeList()) {
            list.append("\t").append(ptr).append(". From <").append(playerTradePair.first().getUser().getUsername()).append("> Good Name: ").
                    append(playerTradePair.second().getList().getFirst().getName()).append(", Amount : ").
                    append(playerTradePair.second().getList().size()).append("\n");
            ptr++;
        }
        return new Result(true, list.toString());
    }

    public Result tradeResponse(String response, String tradeID) {
        int tradeNumber = Integer.parseInt(tradeID);

        if (!(response.equals("accept") || response.equals("reject")))
            return new Result(false, "Your trade response is not acceptable!");
        if (tradeNumber <= 0 || tradeNumber > App.getCurrentGame().getCurrentPlayer().getTradeList().size())
            return new Result(false, "There is no gift with that number in your trade list!");

        if (response.equals("accept")) {
            tradeNumber--;
            Pair<Player, Trade> trade = App.getCurrentGame().getCurrentPlayer().getTradeList().get(tradeNumber);
            App.getCurrentGame().getCurrentPlayer().getTradeList().remove(trade);
            App.getCurrentGame().getCurrentPlayer().getInventory().addGood(trade.second().getList());
            App.getCurrentGame().getCurrentPlayer().getTradeHistory().add(new Pair<>(trade.first(),
                    "You've done a trade with " + trade.first().getUser().getUsername()
                            + " and received " + trade.second().getList().size() + " amount of " + trade.second().getList().getFirst().getName()));

            trade.first().getGiftHistory().add(new Pair<>(App.getCurrentGame().getCurrentPlayer(),
                    "A trade with " + trade.second().getList().size() +
                            " amount of " + trade.second().getList().getFirst().getName() +
                            " have been done with " + App.getCurrentGame().getCurrentPlayer().getUser().getUsername() + " by you! "));


            if (!App.getCurrentGame().getCurrentPlayer().getIsInteracted().get(trade.first())) {

                int value = 30;
                App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.first(),
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + value));
                trade.first().getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + value));

                System.out.println("Your friendship value with " + trade.first().getUser().getUsername() + " is increased to " +
                        App.getCurrentGame().getCurrentPlayer().getFriendShips().get(trade.first()).second());

                App.getCurrentGame().getCurrentPlayer().getIsInteracted().put(trade.first(), true);
                trade.first().getIsInteracted().put(App.getCurrentGame().getCurrentPlayer(), true);

            }

            return new Result(true, "Your have accepted trade with number " + tradeID);
        }
        if (response.equals("reject")) {
            tradeNumber--;
            Pair<Player, Trade> trade = App.getCurrentGame().getCurrentPlayer().getTradeList().get(tradeNumber);

            if (!App.getCurrentGame().getCurrentPlayer().getIsInteracted().get(trade.first())) {

                int value = -30;
                App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(trade.first(),
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + value));
                trade.first().getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + value));

                System.out.println("Your friendship value with " + trade.first().getUser().getUsername() + " is increased to " +
                        App.getCurrentGame().getCurrentPlayer().getFriendShips().get(trade.first()).second());

                App.getCurrentGame().getCurrentPlayer().getIsInteracted().put(trade.first(), true);
                trade.first().getIsInteracted().put(App.getCurrentGame().getCurrentPlayer(), true);

            }

            return new Result(true, "Your have rejected trade with number " + tradeID);
        }
        return new Result(true, "");
    }

    public Result tradeHistory() {
        StringBuilder list = new StringBuilder();
        list.append("Trade History:\n");
        int ptr = 1;
        for (Pair<Player, String> tradeHistory : App.getCurrentGame().getCurrentPlayer().getTradeHistory()) {
            list.append("\t").append(ptr++).append(". ").append(tradeHistory.second()).append("\n");
        }

        return new Result(true, list.toString());
    }

    public Result exitTradeMenu() {
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You are now exiting trade");
    }

}
