package com.StardewValley.controllers;

import com.StardewValley.Main;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.Menu;
import com.StardewValley.models.interactions.User;
import com.StardewValley.views.MainMenuView;
import com.StardewValley.views.ProfileMenuView;

import java.util.Random;
import java.util.Scanner;

public class ProfileMenuController extends Controller {
    private ProfileMenuView view;


    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleProfile() {
        if (view == null) {
            return;
        }

        if (view.getUsernameChangeButton().isChecked()) {
            view.getUsernameChangeButton().setChecked(false);

            Result res = changeUsername(view.getUsernameField().getText());
            view.getErrorLabel().setText(res.message());
        }
        else if (view.getPasswordChangeButton().isChecked()) {
            view.getPasswordChangeButton().setChecked(false);

            Result res = changePassword(view.getPasswordField().getText());
            view.getErrorLabel().setText(res.message());
        }
        else if (view.getEmailChangeButton().isChecked()) {
            view.getEmailChangeButton().setChecked(false);

            Result res = changeEmail(view.getEmailField().getText());
            view.getErrorLabel().setText(res.message());
        }
        else if (view.getNicknameChangeButton().isChecked()) {
            view.getNicknameChangeButton().setChecked(false);

            Result res = changeNickname(view.getNicknameField().getText());
            view.getErrorLabel().setText(res.message());
        }
        else if (view.getBackButton().isChecked()) {
            view.getBackButton().setChecked(false);

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), Assets.getInstance().getSkin()));
        }
    }

    public Result changeUsername(String username) {
        // Check Username is new
        if(App.getCurrentUser().getUsername().equals(username)) {
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

        App.getCurrentUser().setUsername(username);
//        DBInteractor.changeUserInDatabase(username,"username");
        return new Result(true, "Your username was successfully changed to " + username + ".");
    }

    public Result changeNickname(String nickname) {
        // Check nickname is new
        if(App.getCurrentUser().getNickname().equals(nickname)) {
            return new Result(false, "Your nickname should be different from the current one.");
        }

        App.getCurrentUser().setNickname(nickname);
//        DBInteractor.changeUserInDatabase(nickname,"nickname");
        return new Result(true, "Your nickname was successfully changed to " + nickname + ".");
    }

    public Result changeEmail(String email) {
        // Check email is new
        if(App.getCurrentUser().getEmail().equals(email)) {
            return new Result(false, "Your email should be different from the current one.");
        }

        // Checking Email Format
        if(!checkEmailFormat(email)) {
            return new Result(false, "Invalid email format!");
        }

        App.getCurrentUser().setEmail(email);
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


        App.getCurrentUser().setPassword(newPassword);
//        DBInteractor.changeUserInDatabase(newPassword,"password");
        return new Result(true, "Your password was successfully changed to " + newPassword + ".");
    }

    public Result userInfo() {
        return new Result(true, App.getCurrentUser().showInfo());
    }

    public Result exit() {
//        App.setCurrentMenu(Menu.MainMenu);

        return new Result(true, "Redirecting to main menu!");
    }

    public Result showCurrentMenu() {
        return new Result(true, "Current Menu : Profile Menu");
    }
}
