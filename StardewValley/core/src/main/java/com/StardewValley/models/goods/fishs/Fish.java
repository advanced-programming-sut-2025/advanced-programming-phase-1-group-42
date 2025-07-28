package com.StardewValley.models.goods.fishs;

import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodLevel;
import com.StardewValley.models.goods.GoodType;

public class Fish extends Good {
    private FishType type;
    private GoodLevel level;

    public Fish(FishType type, GoodLevel level) {
        this.type = type;
        this.level = level;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getSellPrice() {
        return (int) (type.getSellPrice() * level.getRatio());
    }

    @Override
    public GoodType getType() {
        return type;
    }

    public GoodLevel getLevel() {
        return level;
    }

    public void setLevel(GoodLevel level) {
        this.level = level;
    }
}
