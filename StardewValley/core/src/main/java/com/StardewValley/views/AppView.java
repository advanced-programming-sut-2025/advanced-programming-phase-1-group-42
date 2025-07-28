package com.StardewValley.views;

import com.StardewValley.models.App;
import com.StardewValley.models.enums.Menu;

import java.util.Scanner;

public class AppView {
    public void run() {
//        Scanner scanner = new Scanner(System.in);

        do {
            App.getCurrentMenu().checkCommand();
        } while (App.getCurrentMenu() != Menu.ExitMenu);

    }
}
