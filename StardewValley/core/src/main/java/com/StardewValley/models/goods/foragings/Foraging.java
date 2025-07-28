package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodLevel;

public abstract class Foraging extends Good {
    protected GoodLevel level;

    public GoodLevel getLevel() {
        return level;
    }
}
