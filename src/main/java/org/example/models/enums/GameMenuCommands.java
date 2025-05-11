package org.example.models.enums;

import org.example.models.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {


    //TODO: Nader
    // Game Commands
    NEW_GAME("\\s*game\\s*new\\s-u\\s(?<username_1>\\S+)\\s(?<username_2>\\S+)\\s(?<username_3>\\S+)\\s*"),
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
    CHEAT_ENERGY_SET("\\s*cheat\\s*energy\\s*set\\s(?<value>\\S+)\\s*"),
    CHEAT_ENERGY_UNLIMITED("\\s*cheat\\s*energy\\s*unlimited\\s*"),
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
    CRAFT_INFO("\\s*craftinfo\\s+-n\\s+(?<craftName>\\S+)\\s*"),


    //TODO: Arani
    // Planting
    PLANT_SEED("\\s*plant\\s-s\\s(?<seed>\\S+)\\s*\\s-d\\s(?<direction>\\S+)\\s*"),
    SHOW_PLANT("\\s*showplant\\s-l\\s(?<x>\\S+)\\s*\\s(?<y>\\S+)\\s*"),
    FERTILIZE("\\s*fertilize\\s-f\\s(?<fertilizer>\\S++)\\s-d\\s(?<direction>\\S+)\\s*"),
    HOW_MUCH_WATER("\\s*howmuch\\s*water\\s*"),


    //TODO: Nader
    // Crafting Commands
    SHOW_CRAFTING_RECIPES("\\s*crafting\\s*show\\s*recipes\\s*"),
    CRAFTING_CRAFT("\\s*crafting\\s*craft\\s(?<item_name>\\S+)\\s*"),
    PLACE_ITEM("\\s*place\\s*item\\s+-n\\s(?<item_name>\\S+)\\s-d\\s(?<direction>\\S+)\\s*"),
    // --> cheat codes
    CHEAT_ADD_ITEM("\\s*cheat\\s*add\\s*item\\s+-n\\s(?<item_name>\\S)\\s+-c\\s(?<count>\\S)\\s*"),

    //TODO: Nader
    // Cooking Commands

    //????????????????????????????????????????????????//
    COOKING_REFRIGERATOR(""),
    SHOW_COOKING_RECIPES("\\s*cooking\\s*show\\s*recipes\\s*"),
    COOKING_PREPARE("\\s*cooking\\s*prepare\\s(?<recipe_name>\\S+)\\s*"),
    EAT("\\s*eat\\s(?<food_name>\\S+)\\s*"),

    //TODO: Parsa
    // Animals & Fishing
    BUILD_BUILDING("\\s*build\\s-a\\s(?<building_name>\\S+)\\s-l\\s(?<x>\\S+)\\s(?<y>\\S+)\\s*"),
    BUY_ANIMAL("\\s*build\\s-a\\s(?<animal_name>\\S+)\\s-n\\s(?<number>\\S+)\\s*"),
    PET_ANIMAL("\\s*pet\\s-n\\s(?<petName>\\S+)\\s*"),
    ANIMAL_LIST("\\s*animal\\s*"),
    CHEAT_SET_ANIMAL_FRIENDSHIP("\\s*cheat\\s*set\\s*friendship\\s-n\\s(?<animal_name>\\S+)\\s-c\\s(?<amount>\\S+)\\s*"),
    SHEPHERD_ANIMAL("\\s*shepherd\\s*animals\\s-n\\s(?<animal_name>\\S+)\\s-l\\s(?<x>\\S+)\\s*\\s(?<y>\\S+)\\s*"),
    FEED_HAY("\\s*feed\\s*hay\\s-n\\s(?<anima_name>\\S+)\\s*"),
    ANIMAL_PRODUCTION_LIST("\\s*produces\\s*"),
    COLLECT_PRODUCT("\\s*collect\\s*produce\\s-n\\s(?<name>\\S+)\\s*"),
    SELL_ANIMAL("\\s*sell\\s*animal\\s+-n\\s(?<name>\\S+)\\s*"),
    FISHING("\\s*fishing\\s+-p\\s(?<fishing_pole>\\S+)\\s*"),

    //TODO: Nader
    // Artisan Commands
    ARTISAN_USE("\\s*artisan\\s*use\\s(?<artisan_name>\\S+)\\s(?<item1_name>\\S+)\\s*"),
    ARTISAN_GET("\\s*artisan\\s*get\\s(?<artisan_name>\\S+)\\s*"),

    //TODO: Nader
    // Sell & Buy Commands
    SHOW_ALL_PRODUCTS("\\s*show\\s*all\\s*products\\s*"),
    SHOW_ALL_AVAILABLE_PRODUCTS("\\s*show\\s*all\\s*available\\s*products\\s*"),
    PURCHASE("\\s*purchase\\s(?<product_name>\\S+)\\s+-n\\s(?<count>\\S+)\\s*"),
    SELL("\\s*sell\\s(?<product_name>\\S+)\\s+-n\\s(?<count>\\S+)\\s*"),
    // --> cheat codes
    CHEAT_ADD_DOLLARS("\\s*cheat\\s*add\\s(?<count>\\S+)\\s*dollars\\s*"),

    //TODO: Arani
    // Friendships
    FRIENDSHIPS("\\s*friendships\\s*"),
    TALK("\\s*talk\\s+-u\\s(?<username>\\S+)\\s+-m\\s(?<message>\\S+)\\s*"),
    TALK_HISTORY("\\s*talk\\s*history\\s+-u\\s(?<username>\\S+)\\s*"),
    GIFT("\\s*gift\\s+-u\\s(?<username>\\S+)\\s+-i\\s(?<item>\\S+)\\s+-a\\s(?<amount>\\S+)\\s*"),
    GIFT_LIST("\\s*gift\\s*list\\s*"),
    GIFT_RATE("\\s*gift\\s*rate\\s+-i\\s(?<gift_number>\\S+)\\s+-r\\s(?<rate>\\S+)\\s*"),
    GIFT_HISTORY("\\s*gift\\s*history\\s+-u\\s(?<username>\\S+)\\s*"),
    HUG("\\s*hug\\s+-u\\s(?<username>\\S+)\\s*"),
    FLOWER("\\s*flower\\s+-u\\s(?<username>\\S+)\\s*"),
    ASK_MARRIAGE("\\s*ask\\s*marriage\\s+-u\\s(?<username>\\S+)\\s+-r\\s(?<ring>\\S+)\\s*"),

    //??????????????????????????????????????????????????????????????//
    RESPOND(""),


    //TODO: Parsa
    //Trading
    START_TRADE("\\s*start\\s*trade\\s*"),

    //TODO: Nader
    // NPC
    MEET_NPC("\\s*meet\\s*NPC\\s(?<npc_name>\\S+)\\s*"),
    GIFT_NPC("\\s*gift\\s*NPC\\s(?<npc_name>\\S+)\\s+-i\\s(?<item>\\S+)\\s*"),
    FRIENDSHIP_NPC_LIST("\\s*friendship\\s*NPC\\s*list\\s*"),
    QUESTS_LIST("\\s*quests\\s*list\\s*"),
    QUESTS_FINISH("\\s*quests\\s*finish\\s+-i\\s(?<index>\\S+)\\s*"),;




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
