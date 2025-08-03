package com.StardewValley.models.enums;

import com.StardewValley.client.views.*;

public enum Menu {
    GameMenu(new GameMenu()),
    TradeMenu(new TradeMenu()),
    ProfileMenu(new ProfileMenu()),
    ExitMenu(new ExitMenu());


    private final AppMenu appMenu;
    Menu(AppMenu appMenu) {
        this.appMenu = appMenu;
    }

    public void checkCommand() {
        this.appMenu.check("");
    }
}
