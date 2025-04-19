package org.example.models;

import java.awt.*;
import java.util.ArrayList;

import org.example.models.enums.Menu;
import org.example.models.game_structure.Game;
import org.example.models.interactions.User;

public class App {
    private static Game game;
    private final static ArrayList<User> users = new ArrayList<>();

    //Function for restoring Users data in App to user them further
    public App() {
        //TODO
    }

    private static Menu currentMenu = Menu.LoginRegisterMenu;

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }


}
