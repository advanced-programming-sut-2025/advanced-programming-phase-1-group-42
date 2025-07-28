package com.StardewValley.models.interactions.Animals;

import com.StardewValley.models.interactions.PlayerBuildings.FarmBuildingTypes;

public enum AnimalTypes {
    CHICKEN("Chicken", 800, "Well cared-for chickens lay eggs every day. Lives in the coop.",
        FarmBuildingTypes.COOP, "GameAssets/Animals/Brown_Chicken.png", "GameAssets/Animals/Petted/petChicken.png"),
    DUCK("Duck", 1200, "Happy lay duck eggs every other day. Lives in the coop.",
        FarmBuildingTypes.BIG_COOP, "GameAssets/Animals/Duck.png","GameAssets/Animals/Petted/petDuck.png"),
    RABBIT("Rabbit", 8000, "These are wooly rabbits! They shed precious wool every few days. Lives in the coop.",
        FarmBuildingTypes.DELUXE_COOP, "GameAssets/Animals/Rabbit.png","GameAssets/Animals/Petted/petRabbit.png"),
    DINOSAUR("Dinosaur", 14000, "The Dinosaur is a farm animal that lives in a Big Coop",
        FarmBuildingTypes.BIG_COOP, "GameAssets/Animals/Dinosaur.png","GameAssets/Animals/Petted/petDino.png"),
    COW("Cow", 1500, "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn.",
        FarmBuildingTypes.BARN, "GameAssets/Animals/Cow.png","GameAssets/Animals/Petted/petCow.png"),
    GOAT("Goat", 4000, "Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn.",
        FarmBuildingTypes.BIG_BARN, "GameAssets/Animals/Goat.png","GameAssets/Animals/Petted/petGoat.png"),
    SHEEP("Sheep", 8000, "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn.",
        FarmBuildingTypes.DELUXE_BARN, "GameAssets/Animals/Sheep.png","GameAssets/Animals/Petted/petSheep.png"),
    PIG("Pig", 16000, "These pigs are trained to find truffles! Lives in the barn.",
        FarmBuildingTypes.DELUXE_BARN, "GameAssets/Animals/Pig.png","GameAssets/Animals/Petted/petPig.png");

    private final String name;
    private final int price;
    private final String description;
    private final FarmBuildingTypes farmBuildingTypes;
    private final String imagePath;
    private final String pettedPath;

    AnimalTypes(String name, int price, String description, FarmBuildingTypes farmBuildingTypes, String imagePath, String pettedPath) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.farmBuildingTypes = farmBuildingTypes;
        this.imagePath = imagePath;
        this.pettedPath = pettedPath;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public FarmBuildingTypes getFarmBuildingTypes() {
        return farmBuildingTypes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getPettedPath() {
        return pettedPath;
    }
}
