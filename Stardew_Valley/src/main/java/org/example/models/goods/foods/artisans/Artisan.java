package org.example.models.goods.foods.artisans;

import org.example.models.Pair;
import org.example.models.goods.GoodType;
import org.example.models.goods.artisans.ArtisanType;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;

public class Artisan extends Food {

    private Pair<GoodType, Integer> ingredients;
    private ArtisanType artisanType;

    public Artisan(FoodType foodType) {
        super(foodType);
    }

    @Override
    public String getName() {
        return artisanType.getName();
    }

    @Override
    public int getPrice() {
        return artisanType.getSellPrice() ;
    }

    public ArtisanType getType() {
        return artisanType;
    }


    public int getSellPrice() {
        return this.artisanType.getSellPrice(ingredients);
    }


}
