package org.example.models.enums;

import org.example.models.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {


    //TODO: Nader
    // Game Commands
    NEW_GAME("\\s*game\\s*new\\s-u\\s(?<username_1>\\S+)\\s(?<username_1>\\S+)\\s(?<username_2>\\S+)\\s(?<username_3>\\S+)\\s*"),
    GAME_MAP("\\s*game\\s*map\\s(?<map_number>\\S+)\\s*"),
    LOAD_GAME("\\s*load\\s*game\\s*"),
    EXIT_GAME("\\s*exit\\s*game\\s*"),
    NEXT_TURN("\\s*next\\s*turn\\s*"),

    //TODO: Nader
    //  Date & Time Commands
    TIME("\\s*time\\s*"),
    DATE("\\s*date\\s*"),
    DATE_TIME("\\s*datetime\\s*"),
    DAY_OF_THE_WEEK("\\s*day\\s*of\\s*the\\s*week\\s*"),
    SEASON("\\s*season\\s*"),
    // --> cheat codes
    CHEAT_ADVANCE_TIME("\\s*cheat\\s*advance\\s*time\\s(?<X>\\S+)\\sh\\s*"),
    CHEAT_ADVANCE_DATE("\\s*cheat\\s*advance\\s*date\\s(?<X>\\S+)\\sd\\s*"),

    //TODO: Parsa
    //Weather
    CHEAT_THUNDER("\\s*cheat\\s+thunder\\s(?<x>\\S+)\\s(?<y>\\S+)\\s*"),
    WEATHER("\\s*weather\\s*"),
    WEATHER_FORECAST("\\s*weather\\s+forecast\\s*"),
    CHEAT_WEATHER_SET("\\s*cheat\\s+weather\\s+set\\s(?<weather>\\S+)\\s*"),
    GREEN_HOUSE_BUILD("\\s*green\\s+house\\s+build\\s*"),

    //TODO: Parsa
    //Map
    WALK("\\s*walk\\s-l\\s(?<x>\\S)\\s(?<y>\\S)\\s*"),
    PRINT_MAP("\\s*print\\s*map\\s-l\\s(?<x>\\S+)\\s(?<y>\\S+)\\s*\\s-s\\s(?<size>\\S+)\\s*"),
    HELP_READING_MAP("\\s*help\\s*reading\\s*map\\s*"),

    //TODO: Parsa
    //inventory & Energy
    ENERGY_SHOW("\\s*show\\s+energy\\s*"),
    CHEAT_ENERGY_SET("cheat energy set\\s(?<value>\\S+)\\s*"),
    CHEAT_ENERGY_UNLIMITED("\\s*cheat\\s+energy\\s+unlimited\\s*"),
    INVENTORY_TRASH_ITEM("\\s*inventory\\s+trash\\s-i\\s(?<item>\\S+)\\s-n\\s(?<number>\\S+)\\s*"),
    INVENTORY_SHOW("\\s*inventory\\s+show\\s*"),

    //TODO: Arani
    // Tools
    TOOLS_EQUIPMENT("\\s*tools\\s*equip\\s(?<tool_name>\\S+)\\s*"),
    TOOLS_SHOW_CURRENT("\\s*tools\\s*show\\s*current\\s*"),
    TOOLS_SHOW_AVAILABLE("\\s*tools\\s*show\\s*available\\s*"),
    TOOLS_UPGRADE("\\s*tools\\s*upgrade\\s(?<tool_name>\\S+)\\s*"),
    TOOLS_USE("\\s*tools\\s*use\\s-d\\s(?<direction>\\S+)\\s*"),


    //TODO: Arani
    // Craft Info
    CRAFT_INFO("\\s*craftinfo\\s-n\\s(?<craft_name>\\S+)\\s*"),


    //TODO: Arani
    // Planting
    PLANT_SEED("\\s*plant\\s-s\\s(?<seed>\\S+)\\s*\\s-d\\s(?<direction>\\S+)\\s*"),
    SHOW_PLANT("\\s*showplant\\s-l\\s(?<x>\\S+)\\s*\\s(?<y>\\S+)\\s*"),
    FERTILIZE("\\s*fertilize\\s-f\\s(?<fertilizer>\\S++)\\s-d\\s(?<direction>\\S+)\\s*"),
    HOW_MUCH_WATER("\\s*howmuch\\s*water\\s*"),


    //TODO: Nader
    // Crafting Commands
    SHOW_CRAFTING_RECIPES("crafting show recipes"),
    CRAFTING_CRAFT("crafting craft\\s(?<item_name>\\S+)\\s*"),
    PLACE_ITEM("place item -n\\s(?<item_name>\\S+)\\s-d\\s(?<direction>`\\S+`"),
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
    BUILD_BUILDING("\\s*build\\s-a\\s(?<building_name>\\S+)\\s-l\\s(?<x>\\S+)\\s(?<y>\\S+)\\s*"),
    BUY_ANIMAL("\\s*build\\s-a\\s(?<building_name>\\S+)\\s-n\\s(?<number>\\S+)\\s*"),
    PET_ANIMAL("\\s*pet\\s-n\\s(?<petName>\\S+)\\s*"),
    ANIMAL_LIST("\\s*animal\\s*"),
    CHEAT_SET_ANIMAL_FRIENDSHIP("\\s*cheat\\s*set\\s*friendship\\s-n\\s(?<animal name>\\S+)\\s-c\\s(?<amount>\\S+)\\s*"),
    SHEPHERD_ANIMAL("\\s*shepherd\\s*animals\\s-n\\s(?<animal_name>\\S+)\\s-l\\s(?<x>\\S+)\\s*\\s(?<y>\\S+)\\s*"),
    FEED_HAY("\\s*feed\\s*hay\\s-n\\s(?<anima_name>\\S+)\\s*"),
    ANIMAL_PRODUCTION_LIST("\\s*produces\\s*"),
    COLLECT_PRODUCT("\\s*collect\\s*produce\\s-n\\s(?<name>\\S+)\\s*"),
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
