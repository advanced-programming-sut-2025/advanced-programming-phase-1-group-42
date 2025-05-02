package org.example.models.goods.artisans;

import org.example.models.goods.Good;

public class Artisan extends Good {

    ArtisanType artisanType;
    @Override
    public String getName() {
        return artisanType.getName();
    }

    @Override
    public int getPrice() {
        return artisanType.getSellPrice() ;
    }

    public ArtisanType getArtisanType() {
        return artisanType;
    }

    public int getEnergy() {
        return artisanType.getEnergy();
    }

}
