package org.example.models.enums;

import org.example.views.*;

import java.util.Scanner;

public enum Menu {
    LoginRegisterMenu(new LoginRegisterMenu()),
    MainMenu(new MainMenu()),
    GameMenu(new GameMenu()),
    ProfileMenu(new ProfileMenu()),
    ExitMenu(new ExitMenu());


    private final AppMenu appMenu;
    Menu(AppMenu appMenu) {
        this.appMenu = appMenu;
    }

    public void checkCommand(Scanner scanner) {
        this.appMenu.check(scanner);
    }
}
