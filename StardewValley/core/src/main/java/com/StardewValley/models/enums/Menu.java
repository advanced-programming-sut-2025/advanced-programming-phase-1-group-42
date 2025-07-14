package com.StardewValley.models.enums;

import com.StardewValley.views.*;

import java.util.Scanner;

public enum Menu {
    LoginRegisterMenu(new LoginRegisterMenu()),
    MainMenu(new MainMenu()),
    GameMenu(new GameMenu()),
    TradeMenu(new TradeMenu()),
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
