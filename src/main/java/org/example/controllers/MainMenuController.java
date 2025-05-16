package org.example.controllers;

import org.example.models.App;
import org.example.models.DBInteractor;
import org.example.models.Result;
import org.example.models.enums.Menu;

import java.util.zip.ZipEntry;

public class MainMenuController extends Controller {

    //TODO: Arani
    public Result menuEnter(String menuName) {
        switch (menuName) {
            case "avatar":
                //TODO
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
        DBInteractor.saveUser();
        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, "Goodbye!");
    }

    public Result logout() {
        App.setCurrentMenu(Menu.LoginRegisterMenu);
        App.getCurrentUser().setStayLogin(false);
        App.setCurrentUser(null);

        return new Result(true, "You have logged out! Redirecting to login/register menu!");
    }

    public Result showCurrentMenu() {
        return new Result(true, "Current Menu : Main Menu");
    }
}
