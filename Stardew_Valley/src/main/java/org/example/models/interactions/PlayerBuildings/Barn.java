package org.example.models.interactions.PlayerBuildings;

import org.example.models.game_structure.Coordinate;
import org.example.models.goods.Good;
import org.example.models.interactions.Animals.Animal;

import java.util.ArrayList;

public class Barn extends FarmBuilding {
    BarnType barnType;

    public Barn(Coordinate cordinate , BarnType barnType) {
        centerCordinate = cordinate;
    }

    ArrayList<Animal> animals = new ArrayList<>();
    public Barn(BarnType barnType) {
        this.barnType = barnType;
    }

    @Override
    public boolean addAnimal(Animal animal) {
        if (animals.size() < barnType.getCapacity()) {
            animals.add(animal);
            return true;
        }
        return false;
    }


    @Override
    public ArrayList<Animal> getAnimals() {
       return animals;
    }

    @Override
    public String getName() {
        return barnType.getName();
    }
}
