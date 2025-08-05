package com.StardewValley.server.controllers;

import com.StardewValley.client.Main;
import com.StardewValley.client.AppClient;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Message;
import com.StardewValley.models.Result;
import com.StardewValley.models.interactions.User;
import com.StardewValley.client.views.MainMenuView;
import com.StardewValley.client.views.ProfileMenuView;
import com.StardewValley.server.ClientHandler;

public class ProfileMenuController extends Controller {
    private ClientHandler clientHandler;

    public ProfileMenuController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Message handleMessage(Message message) {
        return null;
    }


    public void handleProfile() {
//        if (view == null) {
//            return;
//        }
//
//        if (view.getSaveButton().isChecked()) {
//            view.getSaveButton().setChecked(false);
//
//            if (view.getUsernameField().getText().equals(AppClient.getCurrentUser().getUsername())) {
//                Result res = changeUsername(view.getUsernameField().getText());
//                if (!res.success()) {
//                    view.getErrorLabel().setText(res.message());
//                    return;
//                }
//            }
//            if (view.getPasswordField().getText().equals(AppClient.getCurrentUser().getPassword())) {
//                Result res = changePassword(view.getPasswordField().getText());
//
//                if (!res.success()) {
//                    view.getErrorLabel().setText(res.message());
//                    return;
//                }
//            }
//            if (view.getEmailField().getText().equals(AppClient.getCurrentUser().getEmail())) {
//                Result res = changeEmail(view.getEmailField().getText());
//
//                if (!res.success()) {
//                    view.getErrorLabel().setText(res.message());
//                    return;
//                }
//            }
//            if (view.getNicknameField().getText().equals(AppClient.getCurrentUser().getNickname())) {
//                Result res = changeNickname(view.getNicknameField().getText());
//
//                if (!res.success()) {
//                    view.getErrorLabel().setText(res.message());
//                    return;
//                }
//            }
//
//        }
//        else if (view.getBackButton().isChecked()) {
//            view.getBackButton().setChecked(false);
//
//            Main.getMain().getScreen().dispose();
//            Main.getMain().setScreen(new MainMenuView(Assets.getInstance().getSkin()));
//        }
    }

    public Result changeUsername(String username) {
        // Check Username is new
        if(AppClient.getCurrentUser().getUsername().equals(username)) {
            return new Result(false, "Your new username should be different from the current one.");
        }
        // Check username format
        if(!username.matches("[A-Za-z0-9\\-]+")) {
            return new Result(false, "Invalid username format!");
        }

        User user = findAppUser(username);

        // Check Username Existence
        if(user != null) {
            return new Result(false, "Username already exists");
        }

        AppClient.getCurrentUser().setUsername(username);
//        DBInteractor.changeUserInDatabase(username,"username");
        return new Result(true, "Your username was successfully changed to " + username + ".");
    }

    public Result changeNickname(String nickname) {
        // Check nickname is new
        if(AppClient.getCurrentUser().getNickname().equals(nickname)) {
            return new Result(false, "Your nickname should be different from the current one.");
        }

        AppClient.getCurrentUser().setNickname(nickname);
//        DBInteractor.changeUserInDatabase(nickname,"nickname");
        return new Result(true, "Your nickname was successfully changed to " + nickname + ".");
    }

    public Result changeEmail(String email) {
        // Check email is new
        if(AppClient.getCurrentUser().getEmail().equals(email)) {
            return new Result(false, "Your email should be different from the current one.");
        }

        // Checking Email Format
        if(!checkEmailFormat(email)) {
            return new Result(false, "Invalid email format!");
        }

        AppClient.getCurrentUser().setEmail(email);
//        DBInteractor.changeUserInDatabase(email,"email");
        return new Result(true, "Your email was successfully changed to " + email + ".");
    }

    public Result changePassword(String newPassword) {
        // Check Password format
        if(!newPassword.matches("[a-zA-Z0-9?><,\"'\\\\;:/|\\]\\[}{+=)(*&^%$#!]+"))
            return new Result(false, "Invalid newPassword format!");
        // Check Password strength
        if(!checkPasswordStrength(newPassword).success())
            return new Result(false, checkPasswordStrength(newPassword).toString());


        AppClient.getCurrentUser().setPassword(newPassword);
//        DBInteractor.changeUserInDatabase(newPassword,"password");
        return new Result(true, "Your password was successfully changed to " + newPassword + ".");
    }

    public Result userInfo() {
        return new Result(true, AppClient.getCurrentUser().showInfo());
    }

    public Result exit() {
//        App.setCurrentMenu(Menu.MainMenu);

        return new Result(true, "Redirecting to main menu!");
    }

    public Result showCurrentMenu() {
        return new Result(true, "Current Menu : Profile Menu");
    }
}
