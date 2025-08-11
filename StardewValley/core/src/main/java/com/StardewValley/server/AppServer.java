package com.StardewValley.server;

import com.StardewValley.models.Assets;
import com.StardewValley.models.Labi;
import com.StardewValley.models.Pair;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AppServer {
    private static ClientListener clientListener;

    private static boolean ended = false;

    private final static ArrayList<User> users = new ArrayList<>();

    private final static ArrayList<User> onlineUsers = new ArrayList<>();

    private final static ArrayList<Game> games = new ArrayList<>();

    private final static ArrayList<Game> offlineGames = new ArrayList<>();

    private final static HashMap<Game, ArrayList<Boolean>> waitTerminateGames = new HashMap<>();

    private final static ArrayList<Labi> labies = new ArrayList<>();

    private final static ArrayList<Pair<Labi, ArrayList<Pair<User, Integer>>>> waitingLabies = new ArrayList<>();

    private final static ArrayList<Pair<Game, ArrayList<User>>> loadWaitingGames = new ArrayList<>();

    private final static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private final static Object lock = new Object();

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
                if(player.getUsername().equals(user.getUsername())) {
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

    public static ArrayList<Pair<Labi, ArrayList<Pair<User, Integer>>>> getWaitingLabies() {
        return waitingLabies;
    }

    public static ArrayList<Game> getOfflineGames() {
        return offlineGames;
    }

    public static Object getLock() {
        return lock;
    }

    public static HashMap<Game, ArrayList<Boolean>> getWaitTerminateGames() {
        return waitTerminateGames;
    }

    public static ArrayList<Pair<Game, ArrayList<User>>> getLoadWaitingGames() {
        return loadWaitingGames;
    }

}
