package org.example.models.interactions.game_buildings;

import org.example.models.goods.foods.FoodType;

import java.util.ArrayList;

public enum ShopType {
    BLACKSMITH("Blacksmith", 16, 9 ,
            new ArrayList<>(
                    new Quadruple<>("Copper Ore")
            )),
    JOJAMART("JojaMart", 23, 9),
    PIERRE_GENERAL_STORE("Pierre’s General Store", 17, 9),
    CARPENTER_SHOP("Carpenter’s Shop", 20, 9),
    FISH_SHOP("Fish Shop", 17, 9),
    MARNIE_RANCH("Marnie’s Ranch", 16, 9),
    STARDROP_SALOON("The Stardrop Saloon", 24, 12);

    private final String storeName;
    private final int closeTime;
    private final int openTime;
    private final ArrayList<Quadruple<FoodType, String,Integer,Integer>> storeItems;

    ShopType(String storeName,
             int closeTime,
             int openTime,
             ArrayList<Quadruple<FoodType, String,Integer,Integer>> storeItems) {
        this.storeName = storeName;
        this.closeTime = closeTime;
        this.openTime = openTime;
        this.storeItems = storeItems;
    }

}
