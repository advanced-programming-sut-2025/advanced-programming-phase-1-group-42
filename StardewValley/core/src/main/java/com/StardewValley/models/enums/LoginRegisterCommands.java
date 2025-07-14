package com.StardewValley.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginRegisterCommands implements Command {
    Exit("\\s*menu\\s+exit\\s*"),

    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),

    Register("\\s*register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>(\\S+\\s+\\S+)|RANDOM_PASSWORD)\\s+" +
            "-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-g\\s+(?<gender>\\S+)\\s*"),

    PickQuestion("\\s*pick\\s+question\\s+-q\\s+(?<questionNumber>\\S+)\\s+-a\\s+(?<answer>\\S+)\\s+-c\\s+" +
            "(?<answerConfirm>\\S+)\\s*"),

    LOGIN("\\s*login\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)(\\s+(?<stayLogin>\\S+))?\\s*"),
    ForgetPassword("\\s*forget\\s+password\\s+-u\\s+(?<username>\\S+)\\s*"),

    AnswerQuestion("\\s*answer\\s*-a\\s*(?<answer>\\S+)\\s*");


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
