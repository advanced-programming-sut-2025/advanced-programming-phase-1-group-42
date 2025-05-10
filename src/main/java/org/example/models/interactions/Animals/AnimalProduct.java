package org.example.models.interactions.Animals;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

public class AnimalProduct extends Good {
    private AnimalProductsType animalProductType;
    private AnimalProductQuality animalProductQuality;

    public AnimalProduct(AnimalProductsType animalProductType , AnimalProductQuality animalProductQuality) {
        this.animalProductType = animalProductType;
        this.animalProductQuality = animalProductQuality;
    }

    @Override
    public String getName() {
        return animalProductType.name();
    }

    @Override
    public int getSellPrice() {
        return (int) (animalProductType.getSellPrice()*animalProductQuality.getRatio());
    }

    @Override
    public GoodType getType() {
        return null;
    }

    public String getQuality() {
        return animalProductQuality.name();
    }
}
