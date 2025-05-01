package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
<<<<<<< Updated upstream
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;
=======
>>>>>>> Stashed changes

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

    public static boolean decreaseGoods(ArrayList<Good> goods, int number) {
        if(goods.size() < number)
            return false;

        for(int i = 0; i < number; i++) {
            goods.removeLast();
        }
        return true;
    }

    public ArrayList<Good> isInInventory(Good good) {
        for (int i = 0; i < size; i++) {
            if(!list.get(i).isEmpty() && list.get(i).getFirst().getName().equals(good.getName())) {
                return list.get(i);
            }
        }
        return null;
    }


<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
}
