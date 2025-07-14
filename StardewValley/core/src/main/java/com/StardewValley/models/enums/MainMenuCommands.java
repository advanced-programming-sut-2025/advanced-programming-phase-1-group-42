package com.StardewValley.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands implements Command {
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    MenuEnter("\\s*menu\\s+enter\\s+(?<targetMenu>avatar|profile|game)\\s*"),
    Exit("\\s*menu\\s+exit\\s*"),
    Logout("\\s*user\\s+logout\\s*");


    private final String pattern;
    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }


    @Override
    public Matcher matcher(String input){
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if(matcher.matches())
            return matcher;

        return null;
    }
}
