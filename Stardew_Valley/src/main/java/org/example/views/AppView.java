package org.example.views;

import org.example.models.App;
import org.example.models.enums.Menu;

import java.util.Scanner;

public class AppView {
    public void run() {
        Scanner scanner = new Scanner(System.in);

        do {
            App.getCurrentMenu().checkCommand(scanner);
        } while (App.getCurrentMenu() != Menu.ExitMenu);

    }
}
