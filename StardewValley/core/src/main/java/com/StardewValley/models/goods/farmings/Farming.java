package com.StardewValley.models.goods.farmings;

import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodLevel;

public abstract class Farming extends Good {
    protected GoodLevel level;

    public GoodLevel getLevel() {
        return level;
    }
}
