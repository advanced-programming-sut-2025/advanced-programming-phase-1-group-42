package com.StardewValley.server.controllers;

import com.StardewValley.Main;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.client.views.GameMenuView;
import com.StardewValley.client.views.LoginMenuView;
import com.StardewValley.client.views.MainMenuView;
import com.StardewValley.client.views.ProfileMenuView;

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
}
