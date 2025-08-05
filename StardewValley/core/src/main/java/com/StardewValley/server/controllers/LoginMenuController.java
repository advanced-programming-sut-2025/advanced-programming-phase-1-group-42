package com.StardewValley.server.controllers;

import com.StardewValley.client.AppClient;
import com.StardewValley.client.Main;
import com.StardewValley.client.views.LoginMenuView;
import com.StardewValley.client.views.MainMenuView;
import com.StardewValley.models.Assets;
import com.StardewValley.models.JSONUtils;
import com.StardewValley.models.Message;
import com.StardewValley.models.Result;
import com.StardewValley.models.interactions.User;
import com.StardewValley.server.AppServer;
import com.StardewValley.server.ClientHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginMenuController extends Controller {
    private ClientHandler clientHandler;

    public LoginMenuController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Message handleMessage(Message message) {
        if (message.getType() == Message.Type.change) {
            switch ((String) message.getFromBody("field")) {
                case "controller" -> {
                    if (message.getFromBody("change") != null &&
                            message.getFromBody("change").equals("RegisterMenuController")) {
                        this.clientHandler.setCurrentController(new RegisterMenuController(clientHandler));
                    }
                    if (message.getFromBody("change") != null &&
                            message.getFromBody("change").equals("MainMenuController")) {
                        this.clientHandler.setCurrentController(new MainMenuController(clientHandler));
                    }

                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", "");
                    }}, Message.Type.response);
                }
            }
        }
        if (message.getType() == Message.Type.command) {
            switch ((String) message.getFromBody("function")) {
                case "login" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return login(arguments.get(0), arguments.get(1), Boolean.parseBoolean(arguments.get(2)));
                }
                case "changePassword" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return changePassword(arguments.get(0), arguments.get(1), arguments.get(2));
                }
                case "getUser" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return getUser(arguments.getFirst());
                }
            }
        }
            return new Message(new HashMap<>() {{
            put("success", false);
            put("message", "");
        }}, Message.Type.response);
    }

    private Message changePassword(String username, String newPassword, String confirmNewPassword) {
        User user = JSONUtils.fromJsonUser(getUser(username).getFromBody("message"));
        if (user == null) {
            return new Message(new HashMap<>() {{
                put("success", false);
                put("message", "User not found!");
            }}, Message.Type.response);
        }

        if (!newPassword.equals(confirmNewPassword)) {
            return new Message(new HashMap<>() {{
                put("success", false);
                put("message", "Password and confirm password do not match!");
            }}, Message.Type.response);
        }

        user.setPassword(newPassword);
        return new Message(new HashMap<>() {{
            put("success", false);
            put("message", "Password has been successfully changed!");
        }}, Message.Type.response);
    }

    private Message login(String username, String password, boolean stayLoggedIn) {
        User user = findAppUser(username);
        if (user == null) {
            return new Message(new HashMap<>() {{
                put("success", false);
                put("message", "User not found!");
            }}, Message.Type.response);
        }

        if (!user.getPassword().equals(getSHA256(password))) {
            return new Message(new HashMap<>() {{
                put("success", false);
                put("message", "Wrong password!");
            }}, Message.Type.response);

        }

        if (stayLoggedIn) {
            user.setStayLogin(true);
        }
        //TODO: STAY LOGIN should be fixed

        clientHandler.setClientUser(user);
        AppServer.getOnlineUsers().add(user);
        return new Message(new HashMap<>() {{
            put("success", true);
            put("message", JSONUtils.toJsonUser(user));
        }}, Message.Type.response);
    }

    private Message getUser(String username) {
        User user = findAppUser(username);
        if(user == null) {
            return new Message(new HashMap<>() {{
                put("success", false);
                put("message", "User not found!");
            }}, Message.Type.response);
        }

        return new Message(new HashMap<>() {{
            put("success", true);
            put("message", JSONUtils.toJsonUser(user));
        }}, Message.Type.response);
    }
}
