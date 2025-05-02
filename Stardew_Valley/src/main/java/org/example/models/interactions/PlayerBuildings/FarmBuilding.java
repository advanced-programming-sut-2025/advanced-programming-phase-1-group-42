package org.example.models.interactions.PlayerBuildings;

import org.example.models.game_structure.Cordinate;
import org.example.models.goods.Good;
import org.example.models.interactions.Animals.Animal;

import java.util.ArrayList;

abstract public class FarmBuilding extends PlayerBuilding {
    abstract public boolean addAnimal(Animal animal);
    abstract public ArrayList<Animal> getAnimals();
    abstract public String getName();


}
