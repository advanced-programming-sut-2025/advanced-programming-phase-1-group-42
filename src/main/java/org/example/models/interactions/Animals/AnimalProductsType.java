package org.example.models.interactions.Animals;

import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;

public enum AnimalProductsType implements GoodType {
    CHICKEN_EGG("Egg",50),
    BIG_CHICKEN_EGG("Big_Egg",95),
    DUCK_EGG("Duck_Egg",95),
    DUCK_FEATHER("Duck_Feather",250),
    RABBIT_WOOL("Rabbit_Wool",340),
    RABBIT_FOOT("Rabbit_Foot",565),
    DINOSAUR_EGG("Dinosaur_Egg",350),
    COW_MILK("Cow_Milk",125),
    BIG_COW_MILK("Big_Cow_Milk",190),
    GOAT_MILK("Goat_Milk",225),
    BIG_GOAT_MILK("Big_Goat_Milk",345),
    SHEEP_WOOL("Sheep_Wool",340),
    TRUFFLE("Truffle",625);
    private final String name;
    private final int price;

    AnimalProductsType(String name, int price) {
        this.name = name;
        this.price = price;

    }
    @Override
    public int getSellPrice() {
        return price;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

}
