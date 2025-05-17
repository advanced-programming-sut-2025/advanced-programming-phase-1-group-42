package org.example.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands implements Command {

    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    ChangeUsername("\\s*change\\s+username\\s+-u\\s+(?<username>\\S+)\\s*"),
    ChangeNickname("\\s*change\\s+nickname\\s+-n\\s+(?<nickname>\\S+)\\s*"),
    ChangeEmail("\\s*change\\s+email\\s+-e\\s+(?<email>\\S+)\\s*"),
    ChangePassword("\\s*change\\s+password\\s+-p\\s+(?<password>\\S+)\\s+-o\\s+(?<oldPassword>\\S+)\\s*"),
    UserInfo("\\s*user\\s+info\\s*"),
    Exit("\\s*menu\\s+exit\\s*"),;


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
