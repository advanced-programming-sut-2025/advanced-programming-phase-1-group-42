package org.example.models.interactions.PlayerBuildings;

import org.example.models.game_structure.Coordinate;
import org.example.models.goods.Good;
import org.example.models.interactions.Animals.Animal;
import org.example.models.interactions.Building;

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
