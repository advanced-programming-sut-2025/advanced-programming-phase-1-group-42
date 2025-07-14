package com.StardewValley.models.goods.artisans;

import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;

public class Artisan extends Good {

    private Pair<GoodType, Integer> ingredients;
    private ArtisanType artisanType;
    private GoodType goodType;

    public Artisan(ArtisanType artisanType) {
        this.artisanType = artisanType;
    }

    @Override
    public String getName() {
        if(goodType != null)
            return artisanType.name() + "_" + goodType.getName();
        return artisanType.getName();
    }

    public int getEnergy() {
        return artisanType.getEnergy(this.goodType);
    }

    public ArtisanType getType() {
        return artisanType;
    }

    @Override
    public int getSellPrice() {
        return artisanType.getSellPrice(this.goodType);
    }

    public void setGoodType(GoodType goodType) {
        this.goodType = goodType;
    }
}
