package com.StardewValley.models.interactions.Animals;

import com.StardewValley.models.goods.GoodType;

public enum AnimalProductsType implements GoodType {
    CHICKEN_EGG("Egg",50, "GameAssets/Animal_product/Egg.png"),
    BIG_CHICKEN_EGG("Big_Egg",95, "GameAssets/Animal_product/Large_Egg.png"),
    DUCK_EGG("Duck_Egg",95, "GameAssets/Animal_product/Duck_Egg.png"),
    DUCK_FEATHER("Duck_Feather",250, "GameAssets/Animal_product/Duck_Feather.png"),
    RABBIT_WOOL("Rabbit_Wool",340, "GameAssets/Animal_product/Wool.png"),
    RABBIT_FOOT("Rabbit_Foot",565, "GameAssets/Animal_product/Rabbit%27s_Foot.png"),
    DINOSAUR_EGG("Dinosaur_Egg",350, "GameAssets/Animal_product/Dinosaur_Egg.png"),
    COW_MILK("Cow_Milk",125, "GameAssets/Animal_product/Milk.png"),
    BIG_COW_MILK("Big_Cow_Milk",190, "GameAssets/Animal_product/Large_Milk.png"),
    GOAT_MILK("Goat_Milk",225, "GameAssets/Animal_product/Goat_Milk.png"),
    BIG_GOAT_MILK("Big_Goat_Milk",345, "GameAssets/Animal_product/Large_Goat_Milk.png"),
    SHEEP_WOOL("Sheep_Wool",340, "GameAssets/Animal_product/Wool.png"),
    TRUFFLE("Truffle",625, "GameAssets/Animal_product/Truffle.png");
    private final String name;
    private final int price;
    private final String image;

    AnimalProductsType(String name, int price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;

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

    @Override
    public String imagePath() {
        return image;
    }

}
