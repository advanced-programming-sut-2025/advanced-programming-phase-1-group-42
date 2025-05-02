package org.example.models.goods.products;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

public class Product extends Good {
    private ProductType type;

    public Product(ProductType type) {
        this.type = type;
    }

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
