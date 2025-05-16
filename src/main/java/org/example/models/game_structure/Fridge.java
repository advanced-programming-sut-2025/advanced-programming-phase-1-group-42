package org.example.models.game_structure;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.Food;

import java.util.ArrayList;
import java.util.Iterator;

public class Fridge {

    private final ArrayList<ArrayList<Good>> inFridgeItems= new ArrayList<>();

    public ArrayList<ArrayList<Good>> getInFridgeItems() {
        return inFridgeItems;
    }

    public Fridge (){
        for (int i = 0; i < 20; i++) {
            inFridgeItems.add(new ArrayList<>());
        }
    }

    public void addItemToFridge(Good food) {

        for (ArrayList<Good> foods: inFridgeItems) {
            if (!foods.isEmpty() && foods.getFirst().getName().equals(food.getName())) {
                foods.add(Good.newGood(food.getType()));
                return;
            }
        }

        for(ArrayList<Good> foods : inFridgeItems) {
            if(foods.isEmpty()) {
                foods.add(Good.newGood(food.getType()));
                return;
            }
        }
    }

    public int howManyInFridge(GoodType foodType) {
        int count = 0;
        for (ArrayList<Good> foods : inFridgeItems) {
            if (!foods.isEmpty()) {
                for (Good f  : foods) {
                    if (f.getType().getName().equals(foodType.getName())) {
                        count++;
                    }
                }
                if (count > 0) {
                    return count;
                }
            }
        }
        return 0;
    }

    public void removeItemsFromFridge(GoodType type, int count) {
        if (howManyInFridge(type) < count) {
            return;
        }

        int remainingToRemove = count;

        for (ArrayList<Good> goods : inFridgeItems) {
            if (remainingToRemove <= 0) {
                break;
            }

            Iterator<Good> iterator = goods.iterator();
            while (iterator.hasNext() && remainingToRemove > 0) {
                Good good = iterator.next();
                if (good.getType().equals(type)) {
                    iterator.remove();
                    remainingToRemove--;
                }
            }
        }
    }


}
