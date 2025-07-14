package com.StardewValley.models.goods.products;

import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;

public class Product extends Good {
    private ProductType type;

    public Product(ProductType type) {
        this.type = type;
    }



    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getSellPrice() {
        return type.getSellPrice();
    }

    @Override
    public GoodType getType() {
        return type;
    }
}
