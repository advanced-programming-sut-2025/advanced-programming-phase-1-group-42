package org.example.views;

import org.example.controllers.MainMenuController;
import org.example.models.enums.LoginRegisterCommands;
import org.example.models.enums.MainMenuCommands;
import org.example.models.game_structure.Game;
import org.example.models.goods.Good;

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
