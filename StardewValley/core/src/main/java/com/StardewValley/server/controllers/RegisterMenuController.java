package com.StardewValley.server.controllers;

import com.StardewValley.client.Main;
import com.StardewValley.client.AppClient;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Message;
import com.StardewValley.models.Result;
import com.StardewValley.models.interactions.Gender;
import com.StardewValley.models.interactions.User;
import com.StardewValley.client.views.LoginMenuView;
import com.StardewValley.client.views.MainMenuView;
import com.StardewValley.server.AppServer;
import com.StardewValley.server.ClientHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterMenuController extends Controller {
    private ClientHandler clientHandler;

    public RegisterMenuController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Message handleMessage(Message message) {
        if (message.getType() == Message.Type.change) {
            switch ((String) message.getFromBody("field")) {
                case "controller" -> {
                    this.clientHandler.setCurrentController(new LoginMenuController(clientHandler));
                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", "");
                    }}, Message.Type.response);
                }
                case "users" -> {
                    ArrayList<String> arguments = message.getFromBody("change");
                    AppServer.getUsers().add(new User(
                        arguments.get(0),
                        getSHA256(arguments.get(1)),
                        arguments.get(2),
                        arguments.get(3),
                        Gender.findGender(arguments.get(4)),
                        Integer.parseInt(arguments.get(5)),
                        arguments.get(6)
                    ));

                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", "");
                    }}, Message.Type.response);
                }
            }
        }

        if (message.getType() == Message.Type.command) {
            switch ((String) message.getFromBody("function")) {
                case "generateRandomPassword" -> {
                    String randomPassword = generateRandomPassword();
                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", randomPassword);
                    }}, Message.Type.response);
                }
                case "register" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    Result res = register(
                        arguments.get(0),
                        arguments.get(1),
                        arguments.get(2),
                        arguments.get(3),
                        arguments.get(4),
                        arguments.get(5)
                    );

                    return new Message(new HashMap<>() {{
                        put("success", res.success());
                        put("message", res.message());
                    }}, Message.Type.response);
                }
            }
        }

        return new Message(new HashMap<>() {{
            put("success", false);
            put("message", "");
        }}, Message.Type.response);
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

        AppClient.setCurrentUser(user);
//        Main.getMain().getScreen().dispose();
//        Main.getMain().setScreen(new MainMenuView(Assets.getInstance().getSkin()));
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
