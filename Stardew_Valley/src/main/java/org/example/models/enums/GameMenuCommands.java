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
    TIME(""),
    DATE(""),
    DATE_TIME(""),
    DAY_OF_THE_WEEK(""),
    SEASON(""),
    // --> cheat codes
    CHEAT_ADVANCE_TIME(""),
    CHEAT_ADVANCE_DATE(""),

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
    TOOLS_EQUIPMENT(""),
    TOOLS_SHOW_CURRENT(""),
    TOOLS_SHOW_AVAILABLE(""),
    TOOLS_UPGRADE(""),
    TOOLS_USE(""),


    //TODO: Arani
    // Craft Info
    CRAFT_INFO(""),


    //TODO: Arani
    // Planting
    PLANT_SEED(""),
    SHOW_PLANT(""),
    FERTILIZE(""),
    HOW_MUCH_WATER(""),


    //TODO: Nader
    // Crafting Commands
    SHOW_CRAFTING_RECIPES(""),
    CRAFTING_CRAFT(""),
    PLACE_ITEM(""),
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
