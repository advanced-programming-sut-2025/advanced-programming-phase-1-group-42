package org.example.models.goods.foods;

import org.example.models.game_structure.Buff;
import org.example.models.game_structure.BuffType;
import org.example.models.goods.GoodType;

public enum FoodType implements GoodType {

    FRIED_EGG("Fried_Egg", 50, 35, new Buff(BuffType.ENERGY_BUFF,0,0)),

    BAKED_FISH("Baked_Fish", 75, 100, new Buff(BuffType.ENERGY_BUFF,0,0)),

    // Cooking Recipe
    SALAD("Salad", 113, 110, new Buff(BuffType.ENERGY_BUFF,0,0)),

    OMELET("Omelet", 100, 125, new Buff(BuffType.ENERGY_BUFF,0,0)),

    PUMPKIN_PIE("Pumpkin_Pie", 225, 385, new Buff(BuffType.ENERGY_BUFF,0,0)),

    // Cooking Recipe
    SPAGHETTI("Spaghetti", 75, 120, new Buff(BuffType.ENERGY_BUFF,0,0)),

    // Cooking Recipe
    PIZZA("Pizza", 150, 300, new Buff(BuffType.ENERGY_BUFF,0,0)),

    TORTILLA("Tortilla", 50, 50, new Buff(BuffType.ENERGY_BUFF,0,0)),

    MAKI_ROLL("Maki_Roll", 100, 220, new Buff(BuffType.ENERGY_BUFF,0,0)),

    TRIPLE_SHOT_ESPRESSO("Triple_Shot_Espresso", 200, 450, new Buff(BuffType.ENERGY_BUFF,5,100)),

    COOKIE("Cookie", 90, 140, new Buff(BuffType.ENERGY_BUFF,0,0)),

    HASH_BROWNS("Hash_Browns", 90, 120, new Buff(BuffType.FARMING_BUFF,5,1)),

    PANCAKES("Pancakes", 90, 80, new Buff(BuffType.FORAGING_BUFF,11,1)),

    FRUIT_SALAD("Fruit_Salad", 263, 450, new Buff(BuffType.ENERGY_BUFF,0,0)),

    RED_PLATE("Red_Plate", 240, 400, new Buff(BuffType.ENERGY_BUFF,3,50)),

    // Cooking Recipe
    BREAD("Bread", 50, 60, new Buff(BuffType.ENERGY_BUFF,0,0)),

    SALMON_DINNER("Salmon_Dinner", 125, 300, new Buff(BuffType.ENERGY_BUFF,0,0)),

    VEGETABLE_MEDLEY("Vegetable_Medley", 165, 120, new Buff(BuffType.ENERGY_BUFF,0,0)),

    FARMERS_LUNCH("Farmers_Lunch", 200, 150, new Buff(BuffType.FARMING_BUFF,5,1)),

    SURVIVAL_BURGER("Survival_Burger", 125, 180, new Buff(BuffType.FORAGING_BUFF,5,1)),

    DISH_O_THE_SEA("Dish_O_The_Sea", 150, 220, new Buff(BuffType.FISHING_BUFF,5,1)),

    SEAFORM_PUDDING("Seaform_Pudding", 175, 300, new Buff(BuffType.FISHING_BUFF,10,1)),

    MINERS_TREAT("Miners_Treat", 125, 200, new Buff(BuffType.MINING_BUFF,5,1)),

    // Fruit Trees
    APRICOT("Apricot", 38, 59, null),

    CHERRY("Cherry", 38, 80, null),

    BANANA("Banana", 75, 150, null),

    MANGO("Mango", 100, 130, null),

    ORANGE("Orange", 38, 100, null),

    PEACH("Peach", 38, 140, null),

    APPLE("Apple", 38, 100, null),

    POMEGRANATE("Pomegranate", 38, 140, null),

    // Special Tree Products
    SAP("Sap", -2, 2, null),

    COMMON_MUSHROOM("Common_Mushroom", 38, 40, null),

    MYSTIC_SYRUP("Mystic_Syrup", 500, 1000, null),

    // TODO: the price should be get times 2 in Stardrop saloon Shop
    BEER("Beer", 0, 20, null),

    COFFEE("Coffee", 0, 150, null);



    private final String name;
    private final int energy;
    private final int sellPrice;
    private final Buff buff;

    FoodType(String name, int energy, int sellPrice , Buff buff) {
        this.name = name;
        this.energy = energy;
        this.sellPrice = sellPrice;
        this.buff = buff;
    }

    public String getName() {
        return name;
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
