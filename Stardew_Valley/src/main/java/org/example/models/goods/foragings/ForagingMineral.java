package org.example.models.goods.foragings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Arrays;

public class ForagingMineral extends Foraging{
    private MineralType type;


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
