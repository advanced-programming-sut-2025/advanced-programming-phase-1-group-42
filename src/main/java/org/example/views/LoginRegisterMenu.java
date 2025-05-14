package org.example.views;

import org.example.controllers.LoginRegisterMenuController;
import org.example.models.enums.LoginRegisterCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginRegisterMenu implements AppMenu {
    private final LoginRegisterMenuController controller = new LoginRegisterMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();

        Matcher matcher;
        if(LoginRegisterCommands.Exit.matcher(input) != null) {
            System.out.println(controller.exit());
        }
        else if(LoginRegisterCommands.ShowCurrentMenu.matcher(input) != null) {
            System.out.println(controller.showCurrentMenu());
        }
        else if((matcher = LoginRegisterCommands.Register.matcher(input)) != null) {
            System.out.println(controller.register(
                    matcher.group("username").trim(),
                    matcher.group("password").trim(),
                    matcher.group("nickname").trim(),
                    matcher.group("email").trim(),
                    matcher.group("gender").trim(),
                    scanner
            ));
        }
        else if((matcher = LoginRegisterCommands.Login.matcher(input)) != null) {
            System.out.println(controller.login(
                    matcher.group("username").trim(),
                    matcher.group("password").trim(),
                    matcher.group("stayLogin") != null
            ));
        }
        else if((matcher = LoginRegisterCommands.ForgetPassword.matcher(input)) != null) {
            System.out.println(controller.forgetPassword(
                    matcher.group("username").trim(),
                    scanner
            ));
        }
        else
            System.out.println("invalid command!");


    }
}
