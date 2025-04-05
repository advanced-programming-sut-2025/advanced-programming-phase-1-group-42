package org.example.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {
    //TODO: Nader

    //TODO: Nader

    //TODO: Parsa

    //TODO: Parsa

    //TODO: Parsa

    //TODO: Arani

    //TODO: Arani

    //TODO: Nader

    //TODO: Nader

    //TODO: Parsa

    //TODO: Nader

    //TODO: Nader

    //TODO: Arani

    //TODO: Parsa

    //TODO: Nader

    private final String pattern;
    GameMenuCommands(String pattern) {
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
