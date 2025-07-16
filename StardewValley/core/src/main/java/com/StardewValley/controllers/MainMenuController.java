package com.StardewValley.controllers;

import com.StardewValley.Main;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.Menu;
import com.StardewValley.views.GameMenuView;
import com.StardewValley.views.LoginMenuView;
import com.StardewValley.views.MainMenuView;
import com.StardewValley.views.ProfileMenuView;

public class MainMenuController extends Controller {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenu() {
        if (view == null) {
            return;
        }

        if (view.getGameButton().isChecked()) {
            view.getGameButton().setChecked(false);

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameMenuView(new GameMenuController(), Assets.getInstance().getSkin()));
        }
        else if (view.getProfileButton().isChecked()) {
            view.getProfileButton().setChecked(false);

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), Assets.getInstance().getSkin()));
        }
        else if (view.getLogoutButton().isChecked()) {
            view.getLogoutButton().setChecked(false);

            if (App.getCurrentUser().isStayLogin()) {
//             DBInteractor.resetStayLogin();
            }

//          DBInteractor.saveUsers();
            App.getCurrentUser().setStayLogin(false);
            App.setCurrentUser(null);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginRegisterMenuController(), Assets.getInstance().getSkin()));
        }
    }

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
