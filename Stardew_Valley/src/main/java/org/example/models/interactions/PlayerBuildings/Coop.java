package org.example.models.interactions.PlayerBuildings;

import org.example.models.App;
import org.example.models.game_structure.Cordinate;
import org.example.models.interactions.Animals.Animal;

import java.util.ArrayList;

public class Coop extends FarmBuilding {
    CoopType coopType;
    private ArrayList<Animal> animals;

    public Coop(Cordinate cordinate, CoopType coopType) {
        centerCordinate = cordinate;
        this.coopType = coopType;
    }

    public Coop(CoopType coopType) {
        this.coopType = coopType;
    }

    @Override
    public boolean addAnimal(Animal animal) {
        if (animals.size() < coopType.getCapacity()) {
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
        return coopType.getName();
    }
}
