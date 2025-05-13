package org.example.models.goods.artisans;

import org.example.models.Pair;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;

public class Artisan extends Good {

    private Pair<GoodType, Integer> ingredients;
    private ArtisanType artisanType;

    public Artisan(ArtisanType artisanType) {
        this.artisanType = artisanType;
    }

    @Override
    public String getName() {
        return artisanType.getName();
    }

    public int getEnergy() {
        return artisanType.getEnergy();
    }

    public ArtisanType getType() {
        return artisanType;
    }

    @Override
    public int getSellPrice() {
        //return this.artisanType.getSellPrice(ingredients);
        return 0;
    }


}
