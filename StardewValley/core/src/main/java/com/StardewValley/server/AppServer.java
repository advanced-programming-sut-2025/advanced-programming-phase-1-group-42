package com.StardewValley.server;

import com.StardewValley.models.Labi;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.User;

import java.io.IOException;
import java.util.ArrayList;

public class AppServer {
    private static ClientListener clientListener;

    private static boolean ended = false;

    private final static ArrayList<User> users = new ArrayList<>();

    private final static ArrayList<User> onlineUsers = new ArrayList<>();

    private final static ArrayList<Game> games = new ArrayList<>();

    private final static ArrayList<Labi> labies = new ArrayList<>();

    private final static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public static void start() throws IOException {
        if (clientListener != null && !clientListener.isAlive())
            clientListener.start();
        else
            throw new IllegalStateException("Server already started");
    }

    public static ArrayList<User> getOnlineUsers() {
        return onlineUsers;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static Game findGame(User user) {
        for (Game game : games) {
            for (Player player : game.getPlayers()) {
                if(player.getUser().getUsername().equals(user.getUsername())) {
                    return game;
                }
            }
        }
        return null;
    }

    public static ClientListener getClientListener() {
        return clientListener;
    }

    public static void setClientListener(ClientListener clientListener) {
        AppServer.clientListener = clientListener;
    }

    public static boolean isEnded() {
        return ended;
    }

    public static void setEnded(boolean ended) {
        AppServer.ended = ended;
    }

    public static ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    public static ArrayList<Labi> getLabies() {
        return labies;
    }
}
