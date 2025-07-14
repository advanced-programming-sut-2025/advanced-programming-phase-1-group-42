package org.example.views;

import org.example.controllers.GameMenuController;
import org.example.controllers.TradeMenuController;
import org.example.models.enums.GameMenuCommands;
import org.example.models.enums.TradeMenuCommands;
import org.example.models.game_structure.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu implements AppMenu {

    private final TradeMenuController controller = new TradeMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (input != null) {
            Game.writeIntoFile(input);
        }

        Matcher matcher;


        //Trading
         if ((matcher = TradeMenuCommands.TRADE_WITH_MONEY.matcher(input)) != null) {
            System.out.println(controller.tradeWithMoney(matcher.group("receiver"), matcher.group("tradeType")
                    , matcher.group("item"), matcher.group("amount"), matcher.group("price")));
         } else if ((matcher = TradeMenuCommands.TRADE_WITH_GOODS.matcher(input)) != null) {
            System.out.print(controller.tradeWithGoods(matcher.group("receiver"), matcher.group("tradeType")
                    , matcher.group("item"), matcher.group("amount"), matcher.group("targetItem")
                    , matcher.group("targetAmount")));
         } else if ((matcher = TradeMenuCommands.TRADE_SYNTAX_ERROR.matcher(input)) != null) {
            System.out.print(controller.tradeError());
         } else if ((matcher = TradeMenuCommands.TRADE_LIST.matcher(input)) != null) {
             System.out.print(controller.tradeList());
         }  else if ((matcher = TradeMenuCommands.TRADE_RESPONSE.matcher(input)) != null) {
            System.out.println(controller.tradeResponse(matcher.group("status"), matcher.group("id")));
         } else if ((matcher = TradeMenuCommands.TRADE_HISTORY.matcher(input)) != null) {
            System.out.print(controller.tradeHistory());
         } else if ((matcher = TradeMenuCommands.EXIT_TRADE.matcher(input)) != null) {
             System.out.print(controller.exitTradeMenu());
         } else {
             System.out.println("Invalid input");
         }


    }
}