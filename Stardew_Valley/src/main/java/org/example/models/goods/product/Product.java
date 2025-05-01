package org.example.models.goods.product;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

public class Product extends Good {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

    @Override
    public GoodType getType() {
        return null;
    }
}
