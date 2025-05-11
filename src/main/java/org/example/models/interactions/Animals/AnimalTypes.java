package org.example.models.interactions.Animals;

import org.example.models.interactions.PlayerBuildings.FarmBuildingTypes;
import org.example.models.interactions.PlayerBuildings.PlayerBuildingType;
import org.example.models.interactions.game_buildings.GameBuilding;

public enum AnimalTypes {
    CHICKEN("Chicken",800 ,"Well cared-for chickens lay eggs every day. Lives in the coop.", FarmBuildingTypes.COOP),
    DUCK("Duck" , 1200 , "Happy lay duck eggs every other day. Lives in the coop." , FarmBuildingTypes.BIG_COOP),
    RABBIT("Rabbit" , 8000 , "These are wooly rabbits! They shed precious wool every few days. Lives in the coop." , FarmBuildingTypes.DELUXE_COOP),
    DINOSAUR("Dinosaur",14000,"The Dinosaur is a farm animal that lives in a Big Coop" , FarmBuildingTypes.BIG_COOP),
    COW("Cow",1500 , "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn." , FarmBuildingTypes.BARN),
    GOAT("Goat",4000 , "Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn." , FarmBuildingTypes.BIG_BARN),
    SHEEP("Sheep",8000,"Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn.",FarmBuildingTypes.DELUXE_BARN),
    PIG("Pig", 16000 ,"These pigs are trained to find truffles! Lives in the barn.",FarmBuildingTypes.DELUXE_BARN);

    private String name;
    private int price;
    private String description;
    private FarmBuildingTypes farmBuildingTypes;

    AnimalTypes(String name, int price , String description , FarmBuildingTypes farmBuildingTypes) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.farmBuildingTypes = farmBuildingTypes;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {return description;}
    public FarmBuildingTypes getFarmBuildingTypes() {return farmBuildingTypes;}
}
