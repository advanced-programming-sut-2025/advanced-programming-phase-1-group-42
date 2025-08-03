package com.StardewValley.client.views;

import com.StardewValley.server.controllers.ProfileMenuController;
import com.StardewValley.models.enums.LoginRegisterCommands;
import com.StardewValley.models.enums.ProfileMenuCommands;
import com.StardewValley.models.game_structure.Game;

import java.util.regex.Matcher;

public class ProfileMenu implements AppMenu {
    private final ProfileMenuController controller = new ProfileMenuController();

    @Override
    public void check(String input) {
        if (input != null) {
            Game.writeIntoFile(input);
        }

        Matcher matcher;
        if((matcher = ProfileMenuCommands.ChangeUsername.matcher(input)) != null) {
            System.out.println(controller.changeUsername(
                    matcher.group("username")
            ));
        }
        else if((matcher = ProfileMenuCommands.ChangeNickname.matcher(input)) != null) {
            System.out.println(controller.changeNickname(
                    matcher.group("nickname")
            ));
        }
        else if((matcher = ProfileMenuCommands.ChangePassword.matcher(input)) != null) {
            System.out.println(controller.changePassword(
                    matcher.group("password")));
        }
        else if((matcher = ProfileMenuCommands.ChangeEmail.matcher(input)) != null) {
            System.out.println(controller.changeEmail(
                    matcher.group("email")
            ));
        }
        else if(ProfileMenuCommands.UserInfo.matcher(input) != null) {
            System.out.println(controller.userInfo());
        }
        else if(ProfileMenuCommands.Exit.matcher(input) != null) {
            System.out.println(controller.exit());
        } else if(LoginRegisterCommands.ShowCurrentMenu.matcher(input) != null) {
            System.out.println(controller.showCurrentMenu());
        }
        else
            System.out.println("Invalid command!");
    }
}
