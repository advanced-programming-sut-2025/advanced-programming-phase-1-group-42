package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.goods.Good;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;

import java.util.ArrayList;

public class Fridge {

    private final ArrayList<ArrayList<Food>> inFridgeItems= new ArrayList<>();

    public ArrayList<ArrayList<Food>> getInFridgeItems() {
        return inFridgeItems;
    }

    public boolean addItemToFridge(Food food) {

        for (ArrayList<Food> foods : inFridgeItems) {
            if (!foods.isEmpty() && foods.getFirst().getName().equals(food.getName())) {
                foods.add(food);
                return true;
            }
        }

        for(ArrayList<Food> foods : inFridgeItems) {
            if(foods.isEmpty()) {
                foods.add(food);
                return true;
            }
        }
        return false;
    }

}
