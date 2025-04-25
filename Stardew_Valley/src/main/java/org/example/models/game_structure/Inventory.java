package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<ArrayList<Good>> list;
    private int size = 12;

    public ArrayList<ArrayList<Good>> getList() {
        return list;
    }

    public void setList(ArrayList<ArrayList<Good>> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public boolean addGood(Good good, int count) {
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty()) {
                for (Good g : goods) {
                    if (g.getType() == good.getType()) {
                        for (int i = 0; i < count; i++) {
                            goods.add(good);
                        }
                    }
                    return true;
                }
            }
        }

        for (ArrayList<Good> goods : list) {
            if (goods.isEmpty()) {
                for (int i = 0; i < count; i++) {
                    goods.add(good);
                }
                return true;
            }
        }
        return false;
    }

    public int howManyInInventory(Good good) {
        int count = 0;
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty()) {
                for (Good g : goods) {
                    if (g.getType() == good.getType()) {
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

    public int howManyInInventoryByType(GoodType type) {
        int count = 0;
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty()) {
                for (Good g : goods) {
                    if (g.getType() == type) {
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

    public void removeItemsFromInventory(GoodType type , int count) {
        if (!(howManyInInventoryByType(type)>=count)){
            return;
        }
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty()) {
                goods.removeIf(g -> g.getType() == type);
            }
        }

    }


}
