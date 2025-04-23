package org.example.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.example.models.enums.Menu;
import org.example.models.game_structure.Farm;
import org.example.models.game_structure.Game;
import org.example.models.game_structure.Map;
import org.example.models.interactions.User;

public class App {
    private final static ArrayList<User> users = new ArrayList<>();

    private static Menu currentMenu = Menu.LoginRegisterMenu;

    public static ArrayList<Farm> farms = new ArrayList<>();

    private static User currentUser = null;

    private static Game currentGame = null;

    private static ArrayList<String> securityQuestions = new ArrayList<>(Arrays.asList(
            "What was the make and model of your first car?",
            "What city were you born in?",
            "What is your favorite movie of all time?"
    ));



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

    public static ArrayList<String> getSecurityQuestions() {
        return securityQuestions;
    }

    public static void startGame() {

    }
}
