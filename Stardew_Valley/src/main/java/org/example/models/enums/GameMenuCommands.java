package org.example.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {



    //TODO: Parsa

    //TODO: Parsa

    //TODO: Parsa

    //TODO: Arani

    //TODO: Arani

    // Game Commands

    NEW_GAME("game new\\s-u\\s(?<username_1>.*)\\s(?<username_1>.*)\\s(?<username_2>.*)\\s(?<username_3>.*)"),
    GAME_MAP("game map\\s(?<map_number>.*)"),
    LOAD_GAME("load game"),
    EXIT_GAME("exit game"),
    NEXT_TURN("next turn"),


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
    QUESTS_FINISH("");



    //TODO: Parsa

    //TODO: Arani

    //TODO: Parsa


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
