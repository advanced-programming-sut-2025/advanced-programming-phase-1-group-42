package com.StardewValley.server.controllers;

import com.StardewValley.client.Main;
import com.StardewValley.client.AppClient;
import com.StardewValley.models.Assets;
import com.StardewValley.client.views.GameMenuView;
import com.StardewValley.client.views.LoginMenuView;
import com.StardewValley.client.views.MainMenuView;
import com.StardewValley.client.views.ProfileMenuView;
import com.StardewValley.models.Message;

public class MainMenuController extends Controller {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    @Override
    public Message handleMessage(Message message) {
        return null;
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

            if (AppClient.getCurrentUser().isStayLogin()) {
//             DBInteractor.resetStayLogin();
            }

//          DBInteractor.saveUsers();
            AppClient.getCurrentUser().setStayLogin(false);
            AppClient.setCurrentUser(null);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(Assets.getInstance().getSkin()));
        }
    }
}
