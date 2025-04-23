package org.example.models.goods.foods.artisans;

import org.example.models.goods.GoodType;
import org.example.models.goods.foods.Food;

import java.util.HashMap;

public class Artisan extends Food {

    private HashMap<GoodType, Integer> ingredients;
    private ArtisanType artisanType;
    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getPrice() {
        return ;
    }

    public ArtisanType getArtisanType() {
        return artisanType;
    }

//    public int getEnergy() {
//        return ;
//    }

    public void setArtisanType(ArtisanType artisanType) {
        this.artisanType = artisanType;
    }

    public int getSellPrice() {
        return this.artisanType.getSellPrice(ingredients.keySet().iterator().next());
    }
}
