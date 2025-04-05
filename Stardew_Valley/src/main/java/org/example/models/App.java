package org.example.models;

import java.awt.*;
import org.example.models.enums.Menu;

public class App {
    private static Menu currentMenu = Menu.LoginRegisterMenu;

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }
}
