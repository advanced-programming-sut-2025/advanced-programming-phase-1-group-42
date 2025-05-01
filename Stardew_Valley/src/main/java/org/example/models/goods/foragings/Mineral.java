package org.example.models.goods.foragings;

import org.example.models.goods.GoodType;

public class Mineral extends Foraging{

    private MineralType mineralType;

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
