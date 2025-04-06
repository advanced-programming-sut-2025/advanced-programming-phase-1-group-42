package org.example.models.enums;

import org.example.models.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {



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

    //TODO: Arani

    // Game Commands

    NEW_GAME(""),
    GAME_MAP(""),
    LOAD_GAME(""),
    EXIT_GAME(""),
    NEXT_TURN(""),


    //  Date & Time Commands
    TIME(""),
    DATE(""),
    DATE_TIME(""),
    DAY_OF_THE_WEEK(""),
    SEASON(""),
    // --> cheat codes
    CHEAT_ADVANCE_TIME(""),
    CHEAT_ADVANCE_DATE(""),


    // Crafting Commands
    SHOW_CRAFTING_RECIPES(""),
    CRAFTING_CRAFT(""),
    PLACE_ITEM(""),
    // --> cheat codes
    CHEAT_ADD_ITEM(""),


    // Cooking Commands
    COOKING_REFRIGERATOR(""),
    SHOW_COOKING_RECIPES(""),
    COOKING_PREPARE(""),
    EAT(""),


    // Artisan Commands
    ARTISAN_USE(""),
    ARTISAN_GET(""),


    // Sell & Buy Commands
    SHOW_ALL_PRODUCTS(""),
    SHOW_ALL_AVAILABLE_PRODUCTS(""),
    PURCHASE(""),
    SELL(""),
    // --> cheat codes
    CHEAT_ADD_DOLLARS(""),

    // NPC
    MEET_NPC(""),
    GIFT_NPC(""),
    FRIENDSHIP_NPC_LIST(""),
    QUESTS_LIST(""),
    QUESTS_FINISH(""),



    //TODO: Parsa
    //Animals & Fishing
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

    //TODO: Arani

    //TODO: Parsa
    //Trading
    START_TRADE(""),
    TRADE_WITH_MONEY(""),
    TRADE_WITH_GOODS(""),
    TRADE_LIST(""),
    TRADE_RESPONSE(""),
    TRADE_HISTORY("");


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
