package com.StardewValley.server.controllers;

import com.StardewValley.models.Message;
import com.StardewValley.server.AppServer;
import com.StardewValley.server.ClientHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenuController extends Controller {
    private ClientHandler clientHandler;

    public MainMenuController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Message handleMessage(Message message) {
        if (message.getType() == Message.Type.command) {
            switch ((String) message.getFromBody("function")) {
                case "updateOnlineUsers" -> {
                    ArrayList<String> onlineString = new ArrayList<>();
                    AppServer.getOnlineUsers().forEach(onlineUser -> {
                        onlineString.add(onlineUser.getUsername() + ", " + onlineUser.getPlaying() + ", " +
                            onlineUser.getEarnedPoints());
                    });

                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", onlineString);
                    }}, Message.Type.response);
                }
            }
        }
        if (message.getType() == Message.Type.change) {
            switch ((String) message.getFromBody("field")) {
                case "controller" -> {
                    if (message.getFromBody("change") == null)
                        break;

                    if (message.getFromBody("change").equals("GameMenuController")) {
                        this.clientHandler.setCurrentController(new GameMenuController(clientHandler));
                    }
                    if (message.getFromBody("change").equals("ProfileMenuController")) {
                        this.clientHandler.setCurrentController(new ProfileMenuController(clientHandler));
                    }
                    if (message.getFromBody("change").equals("LoginMenuController")) {
                        AppServer.getOnlineUsers().remove(clientHandler.getClientUser());
                        clientHandler.setClientUser(null);
                        this.clientHandler.setCurrentController(new LoginMenuController(clientHandler));
                    }

                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", "to " + message.getFromBody("change"));
                    }}, Message.Type.response);
                }
            }
        }

        return new Message(new HashMap<>() {{
            put("success", false);
            put("message", "");
        }}, Message.Type.response);
    }

}
