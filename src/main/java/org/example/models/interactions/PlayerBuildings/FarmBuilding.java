package org.example.models.interactions.PlayerBuildings;

import org.example.models.game_structure.Coordinate;
import org.example.models.goods.Good;
import org.example.models.interactions.Animals.Animal;

import java.util.ArrayList;

public class FarmBuilding extends PlayerBuilding {
    FarmBuildingTypes type;
    Coordinate centerCoordinate;

    public FarmBuilding(Coordinate coordinate , FarmBuildingTypes type) {
        centerCoordinate = coordinate;
    }

    ArrayList<Animal> animals = new ArrayList<>();
    public FarmBuilding(FarmBuildingTypes type , Coordinate centerCoordinate) {
        this.type = type;
        this.centerCoordinate = centerCoordinate;
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
