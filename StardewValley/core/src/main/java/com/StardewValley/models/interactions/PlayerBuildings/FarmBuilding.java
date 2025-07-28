package com.StardewValley.models.interactions.PlayerBuildings;

import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.interactions.Animals.Animal;
import com.StardewValley.models.interactions.Building;

import java.util.ArrayList;

public class FarmBuilding extends Building {
    FarmBuildingTypes type;

    ArrayList<Animal> animals = new ArrayList<>();
    public FarmBuilding(FarmBuildingTypes type , Coordinate startCoordinate) {
        super(startCoordinate, new Coordinate(startCoordinate.getX() + type.getSize().first(),
                startCoordinate.getY() + type.getSize().second()));

        this.type = type;
    }

    public boolean addAnimal(Animal animal) {
        if (animals.size() < type.getCapacity()) {
            animals.add(animal);
            return true;
        }
        return false;
    }


    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public String getName() {
        return type.getName();
    }

    public FarmBuildingTypes getType() {
        return type;
    }


}
