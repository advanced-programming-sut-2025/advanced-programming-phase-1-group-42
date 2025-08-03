package com.StardewValley.controllers;

import com.StardewValley.Main;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.DBInteractor;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.LoginRegisterCommands;
import com.StardewValley.models.enums.Menu;
import com.StardewValley.models.interactions.Gender;
import com.StardewValley.models.interactions.User;
import com.StardewValley.views.LoginMenuView;
import com.StardewValley.views.MainMenuView;
import com.StardewValley.views.RegisterMenuView;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginRegisterMenuController extends Controller {
    private Screen view;


    public void setView(Screen view) {
        this.view = view;
    }

    public void handleRegister() {
        if (view == null)
            return;

        RegisterMenuView registerView = (RegisterMenuView) view;
        if (registerView.getRandomPasswordButton().isChecked()) {
            registerView.getRandomPasswordButton().setChecked(false);
            String randomPassword = generateRandomPassword();
            registerView.getPasswordField().setText(randomPassword);
            registerView.getConfirmPasswordField().setText(randomPassword);
        }
        else if (registerView.getExitButton().isChecked()) {
            registerView.getExitButton().setChecked(false);
            Main.getBatch().dispose();
        }
        else if (registerView.getLoginButton().isChecked()) {
            registerView.getLoginButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginRegisterMenuController(), Assets.getInstance().getSkin()));
        }
        else if (registerView.getRegisterButton().isChecked()) {
            registerView.getRegisterButton().setChecked(false);

            Result res = register(
                registerView.getUsernameField().getText(),
                registerView.getPasswordField().getText(),
                registerView.getConfirmPasswordField().getText(),
                registerView.getNicknameField().getText(),
                registerView.getEmailField().getText(),
                registerView.getGenderBox().getSelected()
            );

            if(!res.success()) {
                registerView.getMessageLabel().setText(res.message());
                return;
            }

            registerView.getSecurityQuestionWindow().setVisible(true);
            registerView.getSecurityQuestionBox().setSelectedIndex(0);
            registerView.getSecurityQuestionField().setText("yes i was");
        }
        else if (registerView.getBackButton().isChecked()) {
            registerView.getBackButton().setChecked(false);
            registerView.getSecurityQuestionWindow().setVisible(false);
        }
        else if (registerView.getSubmitAnswerButton().isChecked()) {
            registerView.getSubmitAnswerButton().setChecked(false);

//            App.getUsers().add(new User(
//                registerView.getUsernameField().getText(),
//                getSHA256(registerView.getPasswordField().getText()),
//                registerView.getNicknameField().getText(),
//                registerView.getEmailField().getText(),
//                Gender.findGender(registerView.getGenderBox().getSelected()),
//                registerView.getSecurityQuestionBox().getSelectedIndex(),
//                registerView.getSecurityQuestionField().getText())
//            );

            User user = new User(
                registerView.getUsernameField().getText(),
                getSHA256(registerView.getPasswordField().getText()),
                registerView.getNicknameField().getText(),
                registerView.getEmailField().getText(),
                Gender.findGender(registerView.getGenderBox().getSelected()),
                registerView.getSecurityQuestionBox().getSelectedIndex(),
                registerView.getSecurityQuestionField().getText());
            App.getUsers().add(user);
//            try {
//                DBInteractor.saveUsers();
//            }  catch (Exception e) {
//                e.printStackTrace();
//            }

            registerView.getMessageLabel().setText("Your account has been successfully registered!");
            registerView.getSecurityQuestionWindow().setVisible(false);
        }

    }

    public void handleLogin() {
        if (view == null)
            return;

        LoginMenuView loginMenuView = (LoginMenuView) view;
        if (loginMenuView.getBackButton().isChecked()) {
            loginMenuView.getBackButton().setChecked(false);

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new RegisterMenuView(new LoginRegisterMenuController(), Assets.getInstance().getSkin()));
        }
        else if (loginMenuView.getForgetPasswordButton().isChecked()) {
            loginMenuView.getForgetPasswordButton().setChecked(false);

            loginMenuView.initForgetPasswordWindow();
        }
        else if (loginMenuView.getLoginButton().isChecked()) {
            loginMenuView.getLoginButton().setChecked(false);
            login(loginMenuView);
        }
        else if (loginMenuView.getUsernameFindButton().isChecked()) {
            loginMenuView.getUsernameFindButton().setChecked(false);

            User user = getUser(loginMenuView);
            if (user == null) return;


            loginMenuView.getSecurityQuestionLabel().setText("Question: " + App.getSecurityQuestions().
                get(user.getQuestionNumber()));
            loginMenuView.getForgetErrorLabel().setText("Welcome " + user.getUsername());
        }
        else if (loginMenuView.getSecurityQuestionSubmitButton().isChecked()) {
            loginMenuView.getSecurityQuestionSubmitButton().setChecked(false);

            User user = getUser(loginMenuView);
            if (user == null) return;

            if (!loginMenuView.getSecurityQuestionField().getText().equals(user.getAnswer())) {
                loginMenuView.getForgetErrorLabel().setText("Wrong answer to security question!");
                return;
            }

            loginMenuView.getForgetErrorLabel().setText("Please enter your new password!");
        }
        else if (loginMenuView.getRandomNewPasswordButton().isChecked()) {
            loginMenuView.getRandomNewPasswordButton().setChecked(false);

            String randomPassword = generateRandomPassword();
            loginMenuView.getNewPasswordField().setText(randomPassword);
            loginMenuView.getConfirmNewPasswordField().setText(randomPassword);
        }
        else if (loginMenuView.getNewPasswordConfirmButton().isChecked()) {
            loginMenuView.getNewPasswordConfirmButton().setChecked(false);

            changePassword(loginMenuView);
        }
        else if (loginMenuView.getForgetBackButton().isChecked()) {
            loginMenuView.getForgetBackButton().setChecked(false);

            loginMenuView.getForgetPasswordWindow().setVisible(false);
        }
    }

    private void changePassword(LoginMenuView loginMenuView) {
        User user = getUser(loginMenuView);
        if (user == null) return;

        if (!loginMenuView.getNewPasswordField().getText().equals(loginMenuView.getConfirmNewPasswordField().getText())) {
            loginMenuView.getForgetErrorLabel().setText("Password and confirm password do not match!");
            return;
        }

        user.setPassword(loginMenuView.getNewPasswordField().getText());
        loginMenuView.getErrorLabel().setText("Password has been successfully changed!");
        loginMenuView.getForgetPasswordWindow().setVisible(false);
    }

    private void login(LoginMenuView loginMenuView) {
        User user = findAppUser(loginMenuView.getUsernameField().getText());
        if (user == null) {
            loginMenuView.getErrorLabel().setText("User not found!");
            return;
        }

        if (!user.getPassword().equals(getSHA256(loginMenuView.getPasswordField().getText()))) {
            loginMenuView.getErrorLabel().setText("Wrong password!");
            return;
        }

        if (loginMenuView.getStayOnLoginCheckBox().isChecked()) {
            user.setStayLogin(true);
        }
        //TODO: STAY LOGIN should be fixed

        App.setCurrentUser(user);
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(), Assets.getInstance().getSkin()));
    }

    private User getUser(LoginMenuView loginMenuView) {
        User user = findAppUser(loginMenuView.getUsernameForgetField().getText());
        if(user == null) {
            loginMenuView.getForgetErrorLabel().setText("User not found!");
            return null;
        }
        return user;
    }

    public Result exit() {
        return new Result(true, "Goodbye!");
    }

    public Result register(String username, String password, String confirmPassword, String nickname, String email,
                           String gender) {
        username = username.trim();
        password = password.trim();
        nickname = nickname.trim();
        email = email.trim();
        gender = gender.trim();

        User user = findAppUser(username);

        // Check Username Existence
        if(user != null) {
            return new Result(false, "User already exists");
        }

        // Checking Username format
        if(!username.matches("[A-Za-z0-9-]+")) {
            return new Result(false, "Invalid username format!");
        }

        // Checking Email Format
        if(!checkEmailFormat(email)) {
            return new Result(false, "Invalid email format!");
        }

        if(!password.matches("[a-zA-Z0-9?><,\"'\\\\;:/|\\]\\[}{+=)(*&^%$#!]+"))
            return new Result(false, "Invalid password format!");
        if(!checkPasswordStrength(password).success())
            return new Result(false, checkPasswordStrength(password).toString());

        if(!password.equals(confirmPassword)) {
            return new Result(false, "Password and confirm password do not match!");
        }

        return new Result(true, "Redirecting to Security Question Window");
    }
}
