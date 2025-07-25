package com.StardewValley.models.goods.foods;

import com.StardewValley.models.game_structure.Buff;
import com.StardewValley.models.game_structure.BuffType;
import com.StardewValley.models.goods.GoodType;

public enum FoodType implements GoodType {

    FRIED_EGG("Fried_Egg", 50, 35, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Fried_Egg.png"),

    BAKED_FISH("Baked_Fish", 75, 100, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Baked_Fish.png"),

    SALAD("Salad", 113, 110, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Salad.png"),

    OMELET("Omelet", 100, 125, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Omelet.png"),

    PUMPKIN_PIE("Pumpkin_Pie", 225, 385, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Pumpkin_Pie.png"),

    SPAGHETTI("Spaghetti", 75, 120, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Spaghetti.png"),

    PIZZA("Pizza", 150, 300, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Pizza.png"),

    TORTILLA("Tortilla", 50, 50, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Tortilla.png"),

    MAKI_ROLL("Maki_Roll", 100, 220, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Maki_Roll.png"),

    TRIPLE_SHOT_ESPRESSO("Triple_Shot_Espresso", 200, 450, new Buff(BuffType.ENERGY_BUFF, 5, 100),
        "GameAssets/Recipe/Triple_Shot_Espresso.png"),

    COOKIE("Cookie", 90, 140, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Cookie.png"),

    HASH_BROWNS("Hash_Browns", 90, 120, new Buff(BuffType.FARMING_BUFF, 5, 1),
        "GameAssets/Recipe/Hash_Browns.png"),

    PANCAKES("Pancakes", 90, 80, new Buff(BuffType.FORAGING_BUFF, 11, 1),
        "GameAssets/Recipe/Pancakes.png"),

    FRUIT_SALAD("Fruit_Salad", 263, 450, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Fruit_Salad.png"),

    RED_PLATE("Red_Plate", 240, 400, new Buff(BuffType.ENERGY_BUFF, 3, 50),
        "GameAssets/Recipe/Red_Plate.png"),

    BREAD("Bread", 50, 60, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Bread.png"),

    SALMON_DINNER("Salmon_Dinner", 125, 300, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Salmon_Dinner.png"),

    VEGETABLE_MEDLEY("Vegetable_Medley", 165, 120, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Recipe/Vegetable_Medley.png"),

    FARMERS_LUNCH("Farmers_Lunch", 200, 150, new Buff(BuffType.FARMING_BUFF, 5, 1),
        "GameAssets/Recipe/Farmers_Lunch.png"),

    SURVIVAL_BURGER("Survival_Burger", 125, 180, new Buff(BuffType.FORAGING_BUFF, 5, 1),
        "GameAssets/Recipe/Survival_Burger.png"),

    DISH_O_THE_SEA("Dish_O_The_Sea", 150, 220, new Buff(BuffType.FISHING_BUFF, 5, 1),
        "GameAssets/Recipe/Dish_O_The_Sea.png"),

    SEAFORM_PUDDING("Seaform_Pudding", 175, 300, new Buff(BuffType.FISHING_BUFF, 10, 1),
        "GameAssets/Recipe/Seaform_Pudding.png"),

    MINERS_TREAT("Miners_Treat", 125, 200, new Buff(BuffType.MINING_BUFF, 5, 1),
        "GameAssets/Recipe/Miners_Treat.png"),

    // Fruit Trees
    APRICOT("Apricot", 38, 59, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Apricot.png"),

    CHERRY("Cherry", 38, 80, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Cherry.png"),

    BANANA("Banana", 75, 150, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Banana.png"),

    MANGO("Mango", 100, 130, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Mango.png"),

    ORANGE("Orange", 38, 100, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Orange.png"),

    PEACH("Peach", 38, 140, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Peach.png"),

    APPLE("Apple", 38, 100, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Apple.png"),

    POMEGRANATE("Pomegranate", 38, 140, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Pomegranate.png"),

    // Special Tree Products
    SAP("Sap", -2, 2, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Sap.png"),

    COMMON_MUSHROOM("Common_Mushroom", 38, 40, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Common_Mushroom.png"),

    MYSTIC_SYRUP("Mystic_Syrup", 500, 1000, new Buff(BuffType.ENERGY_BUFF, 0, 0),
        "GameAssets/Trees/Mystic_Syrup.png"),

    // TODO: the price should be get times 2 in Stardrop saloon Shop
    BEER("Beer", 0, 20, new Buff(BuffType.ENERGY_BUFF,0,0),
        "GameAssets/Artisan_good/Beer.png"),

    COFFEE("Coffee", 0, 150, new Buff(BuffType.ENERGY_BUFF,0,0)
    ,"GameAssets/Artisan_good/Coffee.png"),

    // Base Types
    WHEAT_FLOUR("Wheat_Flour", -1, -1, new Buff(BuffType.ENERGY_BUFF,0,0),
        "GameAssets/Ingredient/Wheat_Flour.png"),

    SUGAR("Sugar", -1, -1, new Buff(BuffType.ENERGY_BUFF,0,0),
        "GameAssets/Ingredient/Sugar.png"),

    CHEESE("Cheese", -1, -1, new Buff(BuffType.ENERGY_BUFF,0,0),
        "GameAssets/Artisan_good/Cheese.png"),

    RICE("Rice", -1, -1, new Buff(BuffType.ENERGY_BUFF,0,0),
        "GameAssets/Ingredient/Rice.png"),;

    private final String name;
    private final int energy;
    private final int sellPrice;
    private final Buff buff;
    private final String imagePath;

    FoodType(String name, int energy, int sellPrice , Buff buff , String imagePath) {
        this.name = name;
        this.energy = energy;
        this.sellPrice = sellPrice;
        this.buff = buff;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    @Override
    public String imagePath() {
        return imagePath;
    }

    public int getEnergy() {
        return energy;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public Buff getBuff() {
        return buff;
    }


}
