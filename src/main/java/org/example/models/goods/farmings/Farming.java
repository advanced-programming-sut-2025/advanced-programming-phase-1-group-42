package org.example.models.goods.farmings;

import org.example.models.goods.Good;
import org.example.models.goods.GoodLevel;

public abstract class Farming extends Good {
    protected GoodLevel level;

    public GoodLevel getLevel() {
        return level;
    }
}
