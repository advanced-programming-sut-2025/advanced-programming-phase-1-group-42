package org.example.models.interactions.Animals;

import org.example.models.goods.Good;
import org.example.models.goods.GoodLevel;
import org.example.models.goods.GoodType;

public class AnimalProduct extends Good {
    private final AnimalProductsType animalProductType;
    private GoodLevel goodLevel;

    public AnimalProduct(AnimalProductsType animalProductType , GoodLevel animalProductQuality) {
        this.animalProductType = animalProductType;
        this.goodLevel = animalProductQuality;
    }

    @Override
    public String getName() {
        return animalProductType.getName();
    }

    @Override
    public int getSellPrice() {
        return (int) (animalProductType.getSellPrice()*goodLevel.getRatio());
    }

    @Override
    public GoodType getType() {
        return animalProductType;
    }

    public String getQuality() {
        return goodLevel.name().toLowerCase();
    }
}
