package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.goods.Good;
import org.example.models.goods.foods.Food;

import java.util.ArrayList;

public class Fridge {

    private final ArrayList<ArrayList<Food>> inFridgeItems= new ArrayList<>();

    public ArrayList<ArrayList<Food>> getInFridgeItems() {
        return inFridgeItems;
    }
    
}
