package com.StardewValley.controllers;

import com.StardewValley.Main;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.LoginRegisterCommands;
import com.StardewValley.models.enums.Menu;
import com.StardewValley.models.interactions.Gender;
import com.StardewValley.models.interactions.User;
import com.StardewValley.views.LoginMenuView;
import com.StardewValley.views.RegisterMenuView;
import com.badlogic.gdx.Screen;

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
            registerView.getSecurityQuestionField().setText("example: yes i was.");
        }
        else if (registerView.getBackButton().isChecked()) {
            registerView.getBackButton().setChecked(false);
            registerView.getSecurityQuestionWindow().setVisible(false);
        }
        else if (registerView.getSubmitAnswerButton().isChecked()) {
            registerView.getSubmitAnswerButton().setChecked(false);

            App.getUsers().add(new User(
                registerView.getUsernameField().getText(),
                getSHA256(registerView.getPasswordField().getText()),
                registerView.getNicknameField().getText(),
                registerView.getEmailField().getText(),
                Gender.findGender(registerView.getGenderBox().getSelected()),
                registerView.getSecurityQuestionBox().getSelectedIndex(),
                registerView.getSecurityQuestionField().getText())
            );

            registerView.getMessageLabel().setText("Your account has been successfully registered!");
            registerView.getSecurityQuestionWindow().setVisible(false);
        }

    }

    public Result exit() {
        return new Result(true, "Goodbye!");
    }

    public Result showCurrentMenu() {
        return new Result(true, "Current Menu : Login/Register Menu");
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
            return new Result(false, "Passwords does not match!");
        }

        return new Result(true, "Redirecting to Security Question Window");
    }

    public Result login(String username, String password, String stayLoggedIn) {
        username = username.trim();
        password = password.trim();

        User user = findAppUser(username);
        if(user == null) {
            return new Result(false, "User not found!");
        }

        if(!user.getPassword().equals(getSHA256(password))) {
            return new Result(false, "Wrong password!");
        }


        if (stayLoggedIn!= null) {
            if (stayLoggedIn.equals("–stay-logged-in")) {
                user.setStayLogin(true);
            }
        }

        App.setCurrentUser(user);
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "You logged in successfully! Redirecting to Main Menu...");
    }

    public Result forgetPassword(String username, Scanner scanner) {
        username = username.trim();

        User user = findAppUser(username);
        if(user == null) {
            return new Result(false, "User not found!");
        }

//        System.out.println(App.getSecurityQuestions().get(user.getQuestionNumber()));
        String answer = scanner.nextLine();
        Matcher matcher = LoginRegisterCommands.AnswerQuestion.matcher(answer);
        System.out.println(user.getAnswer());
        if(matcher == null || !matcher.group("answer").equals(user.getAnswer()))
            return new Result(false, "Wrong answer to security question!");

        System.out.println("Choose how to you want to reset your password? (1/2)\n1. Random Password\n2. Entering Password");
        int number = 2;
        try {
            number = Integer.parseInt(scanner.nextLine());
            if(number >= 3 || number < 1)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            return new Result(false, "Wrong number format!");
        }

        String newPassword = "";
        if(number == 1) {
            while(true) {
                String rPassword = generateRandomPassword();
                System.out.println("Use this Random Password for your Password? (y/n)");
                String input = scanner.nextLine();
                if(input.equals("y")) {
                    newPassword = rPassword;
                    break;
                }
            }
        }
        else {
            System.out.println("Enter your new password:");
            newPassword = scanner.nextLine();
        }

        user.setPassword(newPassword);
        return new Result(true, "Your Password has been successfully changed!");
    }
}
