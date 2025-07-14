package com.StardewValley.views;

import com.StardewValley.controllers.MainMenuController;
import com.StardewValley.models.enums.LoginRegisterCommands;
import com.StardewValley.models.enums.MainMenuCommands;
import com.StardewValley.models.game_structure.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu {
    private final MainMenuController controller = new MainMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (input != null) {
            Game.writeIntoFile(input);
        }

        Matcher matcher;
        if ((matcher = MainMenuCommands.MenuEnter.matcher(input)) != null) {
            System.out.println(controller.menuEnter(
                    matcher.group("targetMenu").trim()
            ));
        } else if (MainMenuCommands.Logout.matcher(input) != null) {
            System.out.println(controller.logout());
        } else if (MainMenuCommands.Exit.matcher(input) != null) {
            System.out.println(controller.exit());
        } else if (LoginRegisterCommands.ShowCurrentMenu.matcher(input) != null) {
            System.out.println(controller.showCurrentMenu());
        } else
            System.out.println("Invalid command!");
    }
}
