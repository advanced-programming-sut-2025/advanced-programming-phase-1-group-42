package com.StardewValley.models.game_structure;

import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.fishs.FishType;
import com.StardewValley.models.goods.foragings.ForagingMineralType;
import com.StardewValley.models.goods.foragings.ForagingSeedType;
import com.StardewValley.models.goods.products.ProductType;

public enum QuestType {
    Q1(ProductType.COAL,200,2,8),
    Q2(ProductType.GOLD_BAR,300,3,10),
    Q3(ForagingSeedType.CARROT_SEEDS,10,2,12),
    Q4(ForagingSeedType.CORN_SEEDS,3,3,4),
    Q5(ForagingMineralType.DIAMOND,10,4,14),
    Q6(ForagingMineralType.FIRE_QUARTZ,11,2,15),
    Q7(FishType.LEGEND,5,4,20),
    Q8(FishType.SALMON,10,3,6);



    private GoodType productType;
    private int targetNum;
    private int capacity;
    private int time;
    QuestType(GoodType productType, int targetNum, int capacity , int time) {
        this.productType = productType;
        this.targetNum = targetNum;
        this.capacity = capacity;
        this.time = time;
    }

    public GoodType getProductType() {
        return productType;
    }

    public int getTargetNum() {
        return targetNum;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTime() {
        return time;
    }

}
