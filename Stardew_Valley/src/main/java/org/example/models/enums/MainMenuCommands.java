package org.example.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands implements Command {
    //TODO: Arani
    MenuEnter(""),
    Exit(""),
    Logout("");


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
