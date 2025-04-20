package org.example.models;

import java.awt.*;
import java.util.ArrayList;

import org.example.models.enums.Menu;
import org.example.models.game_structure.Game;
import org.example.models.game_structure.Map;
import org.example.models.interactions.User;

public class App {
    private final static ArrayList<User> users = new ArrayList<>();

    private static Menu currentMenu = Menu.LoginRegisterMenu;

    public static ArrayList<Map> maps = new ArrayList<>();

    private static User currentUser = null;

    private static Game currentGame = null;

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }


}
