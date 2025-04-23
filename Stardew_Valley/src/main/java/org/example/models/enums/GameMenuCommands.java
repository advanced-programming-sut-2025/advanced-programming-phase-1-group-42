package org.example.models.enums;

import org.example.models.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {


    //TODO: Nader
    // Game Commands
    NEW_GAME("game new\\s-u\\s(?<username_1>.*)\\s(?<username_1>.*)\\s(?<username_2>.*)\\s(?<username_3>.*)"),
    GAME_MAP("game map\\s(?<map_number>.*)"),
    LOAD_GAME("load game"),
    EXIT_GAME("exit game"),
    NEXT_TURN("next turn"),

    //TODO: Nader
    //  Date & Time Commands
    TIME("time"),
    DATE("date"),
    DATE_TIME("datetime"),
    DAY_OF_THE_WEEK("day of the week"),
    SEASON("season"),
    // --> cheat codes
    CHEAT_ADVANCE_TIME("cheat advance time\\(?<X>.*)h"),
    CHEAT_ADVANCE_DATE("cheat advance date\\(?<X>.*)d"),

    //TODO: Parsa
    //Weather
    CHEAT_THUNDER(""),
    WEATHER(""),
    WEATHER_FORECAST(""),
    CHEAT_WEATHER_SET(""),
    GREEN_HOUSE_BUILD(""),

    //TODO: Parsa
    //Map
    WALK(""),
    PRINT_MAP(""),
    HELP_READING_MAP(""),

    //TODO: Parsa
    //inventory & Energy
    ENERGY_SHOW(""),
    CHEAT_ENERGY_SET(""),
    CHEAT_ENERGY_UNLIMITED(""),
    INVENTORY_TRASH_ITEM(""),
    INVENTORY_SHOW(""),

    //TODO: Arani
    // Tools
    TOOLS_EQUIPMENT("\\s*tools\\s+equip\\s+(?<toolName>\\S+)\\s*"),
    TOOLS_SHOW_CURRENT("\\s*tools\\s+show\\s+current\\s*"),
    TOOLS_SHOW_AVAILABLE("\\s*tools\\s+show\\s+available\\s*"),
    TOOLS_UPGRADE("\\s*tools\\s+upgrade\\s+(?<toolName>\\S+)\\s*"),
    TOOLS_USE("\\s*tools\\s+use\\s+-d\\s+(?<direction>\\S+)\\s+"),


    //TODO: Arani
    // Craft Info
    CRAFT_INFO("\\s*craftinfo\\s+-n\\s+(?<craftName>\\S+)\\s*"),


    //TODO: Arani
    // Planting
    PLANT_SEED("\\s*plant\\s+-s\\s+(?<seedName>\\S+)\\s+-d\\s+(?<direction>\\S+)\\s*"),
    SHOW_PLANT("\\s*showplant\\s+-l\\s+(?<x>\\S+)\\s+(?<y>\\S+)\\s*"),
    FERTILIZE("\\s*fertilize\\s+-f\\s+(?<fertilizer>\\S+)\\s+-d\\s+(?<direction>\\S+)\\s*"),
    HOW_MUCH_WATER(""),


    //TODO: Nader
    // Crafting Commands
    SHOW_CRAFTING_RECIPES("crafting show recipes"),
    CRAFTING_CRAFT("crafting craft\\s(?<item_name>)"),
    PLACE_ITEM("place item -n\\s(?<item_name>.*)\\s-d\\s(?<direction>.*"),
    // --> cheat codes
    CHEAT_ADD_ITEM(""),

    //TODO: Nader
    // Cooking Commands
    COOKING_REFRIGERATOR(""),
    SHOW_COOKING_RECIPES(""),
    COOKING_PREPARE(""),
    EAT(""),

    //TODO: Parsa
    // Animals & Fishing
    BUILD_BUILDING(""),
    BUY_ANIMAL(""),
    PET_ANIMAL(""),
    ANIMAL_LIST(""),
    CHEAT_SET_ANIMAL_FRIENDSHIP(""),
    SHEPHERD_ANIMAL(""),
    FEED_HAY(""),
    ANIMAL_PRODUCTION_LIST(""),
    COLLECT_PRODUCT(""),
    SELL_ANIMAL(""),
    FISHING(""),

    //TODO: Nader
    // Artisan Commands
    ARTISAN_USE(""),
    ARTISAN_GET(""),

    //TODO: Nader
    // Sell & Buy Commands
    SHOW_ALL_PRODUCTS(""),
    SHOW_ALL_AVAILABLE_PRODUCTS(""),
    PURCHASE(""),
    SELL(""),
    // --> cheat codes
    CHEAT_ADD_DOLLARS(""),

    //TODO: Arani
    // Friendships
    FRIENDSHIPS(""),
    TALK(""),
    TALK_HISTORY(""),
    GIFT(""),
    GIFT_LIST(""),
    GIFT_RATE(""),
    GIFT_HISTORY(""),
    HUG(""),
    FLOWER(""),
    ASK_MARRIAGE(""),
    RESPOND(""),


    //TODO: Parsa
    //Trading
    START_TRADE(""),
    TRADE_WITH_MONEY(""),
    TRADE_WITH_GOODS(""),
    TRADE_LIST(""),
    TRADE_RESPONSE(""),
    TRADE_HISTORY(""),


    //TODO: Nader
    // NPC
    MEET_NPC(""),
    GIFT_NPC(""),
    FRIENDSHIP_NPC_LIST(""),
    QUESTS_LIST(""),
    QUESTS_FINISH("");




    private final String pattern;

    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }


    @Override
    public Matcher matcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches())
            return matcher;

        return null;
    }
    }
