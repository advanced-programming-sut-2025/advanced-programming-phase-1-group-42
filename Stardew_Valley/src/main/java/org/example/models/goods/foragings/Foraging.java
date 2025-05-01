package org.example.models.goods.foragings;

import org.example.models.goods.Good;
import org.example.models.goods.GoodLevel;

public abstract class Foraging extends Good {
    protected GoodLevel level;

    public GoodLevel getLevel() {
        return level;
    }
}
