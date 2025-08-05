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

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileMenuController extends Controller {
    private ClientHandler clientHandler;

    public ProfileMenuController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Message handleMessage(Message message) {
        if (message.getType() == Message.Type.command) {
            switch ((String) message.getFromBody("function")) {
                case "changeUsername" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    Result res = changeUsername(arguments.getFirst());
                    return new Message(new HashMap<>() {{
                        put("success", res.success());
                        put("message", res.message());
                    }}, Message.Type.response);
                }
                case "changePassword" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    Result res = changePassword(arguments.getFirst());
                    return new Message(new HashMap<>() {{
                        put("success", res.success());
                        put("message", res.message());
                    }}, Message.Type.response);
                }
                case "changeEmail" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    Result res = changeEmail(arguments.getFirst());
                    return new Message(new HashMap<>() {{
                        put("success", res.success());
                        put("message", res.message());
                    }}, Message.Type.response);
                }
                case "changeNickname" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    Result res = changeNickname(arguments.getFirst());
                    return new Message(new HashMap<>() {{
                        put("success", res.success());
                        put("message", res.message());
                    }}, Message.Type.response);
                }
            }
        }

        if (message.getType() == Message.Type.change) {
            switch ((String) message.getFromBody("field")) {
                case "controller" -> {
                    if (message.getFromBody("change") == null)
                        break;

                    if (message.getFromBody("change").equals("MainMenuController")) {
                        this.clientHandler.setCurrentController(new MainMenuController(clientHandler));
                    }

                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", "");
                    }}, Message.Type.response);

                }
            }
        }

        return new Message(new HashMap<>() {{
            put("success", false);
            put("message", "");
        }}, Message.Type.response);
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
