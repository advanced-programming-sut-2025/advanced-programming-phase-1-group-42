package org.example.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginRegisterCommands implements Command {
    //TODO: Arani
    Exit(""),
    ShowCurrentMenu(""),
    Register(""),
    Login(""),
    ForgetPassword("");


    private final String pattern;
    LoginRegisterCommands(String pattern) {
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
