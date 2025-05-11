package org.example.models.enums;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public enum TradeMenuCommands implements Command{

    //TODO: Parsa
    //Trading

    TRADE_WITH_MONEY("\\s*trade\\s+-u\\s(?<username>\\S+)\\s+-t\\s(?<type>\\S+)\\s+-i\\s(?<item>\\S)\\s+-a\\s(?<amount>\\S+)\\s+-p\\s(?<price>\\S+)\\s*"),
    TRADE_SYNTAX_ERROR("\\s*trade\\s+-u\\s(?<username>\\S+)\\s+-t\\s(?<type>\\S+)\\s+-i\\s(?<item>\\S)\\s+-a\\s(?<amount>\\S+)\\s+-p\\s(?<price>\\S+)\\s+-ti\\s(?<targetItem>\\S+)\\s+-ta\\s(?<targetAmount>\\S+)\\s*"),
    TRADE_WITH_GOODS("\\s*trade\\s+-u\\s(?<username>\\S+)\\s+-t\\s(?<type>\\S+)\\s+-i\\s(?<item>\\S)\\s+-a\\s(?<amount>\\S+)\\s+-ti\\s(?<targetItem>\\S+)\\s+-ta\\s(?<targetAmount>\\S+)\\s*"),
    TRADE_LIST("\\s*trade\\s*list\\s*"),
    TRADE_RESPONSE("\\s*trade\\s*response\\s(?<status>\\S+)\\s+-i\\s(?<id>\\S+)\\s*"),
    TRADE_HISTORY("\\s*trade\\s*history\\s*"),
    EXIT_TRADE("\\s*exit\\s*trade\\s*");

    private final String pattern;
    TradeMenuCommands(String pattern) {
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
