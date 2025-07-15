package com.StardewValley.controllers;

import com.StardewValley.models.App;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.Menu;

public class MainMenuController extends Controller {

    public Result menuEnter(String menuName) {
        switch (menuName) {
            case "avatar":
                break;
            case "profile":
                App.setCurrentMenu(Menu.ProfileMenu);
                break;
            case "game":
                App.setCurrentMenu(Menu.GameMenu);
                break;
            default:
                return new Result(false, "Invalid menu name");
        }

        return new Result(true, "Redirecting to " + menuName + "!");
    }

    public Result exit() {
//        DBInteractor.saveUsers();
//        DBInteractor.setStayLogin();
        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, "Goodbye!");
    }

    public Result logout() {
        if (App.getCurrentUser().isStayLogin()) {
//            DBInteractor.resetStayLogin();
        }
//        DBInteractor.saveUsers();
        App.getCurrentUser().setStayLogin(false);
        App.setCurrentUser(null);

        return new Result(true, "You have logged out! Redirecting to login/register menu!");
    }

    public Result showCurrentMenu() {
        return new Result(true, "Current Menu : Main Menu");
    }
}
