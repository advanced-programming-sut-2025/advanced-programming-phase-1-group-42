package com.StardewValley.controllers;

import com.StardewValley.Main;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.DBInteractor;
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

//            if (App.getCurrentUser().isStayLogin()) {
//                try {
//                    DBInteractor.resetStayLogin();
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }

//            try {
//                DBInteractor.loadUsers();
//                System.out.println("hi");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            App.getCurrentUser().setStayLogin(false);
            App.setCurrentUser(null);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginRegisterMenuController(), Assets.getInstance().getSkin()));
        }
    }
}
