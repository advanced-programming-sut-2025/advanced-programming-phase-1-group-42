package org.example.models.goods.farmings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;

import java.util.ArrayList;

public enum TreeSaplingType implements GoodType {
    ;

    private String name;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private FoodType foodType;
    private int fruitHarvestCycle;
    private int fruitBaseSellPrice;
    private boolean isFruitEdible;
    private int FruitEnergy;
    private ArrayList<Season> seasons;



}
