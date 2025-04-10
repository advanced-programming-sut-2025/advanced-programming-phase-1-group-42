package org.example.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands implements Command {

    //TODO: Arani
    ChangeUsername(""),
    ChangeNickname(""),
    ChangeEmail(""),
    ChangePassword(""),
    UserInfo(""),
    Exit("");


    private final String pattern;
    ProfileMenuCommands(String pattern) {
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
