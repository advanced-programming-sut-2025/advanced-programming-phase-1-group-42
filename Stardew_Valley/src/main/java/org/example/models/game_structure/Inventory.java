package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

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

    public ArrayList<Good> isInInventory(String goodName) {
        for (int i = 0; i < size; i++) {
            if(!list.get(i).isEmpty() && list.get(i).getFirst().getName().equals(goodName)) {
                return list.get(i);
            }
        }
        return null;
    }

    public ArrayList<Good> isInInventory(GoodType goodType) {
        for (int i = 0; i < size; i++) {
            if(!list.get(i).isEmpty() && list.get(i).getFirst().getType() == goodType) {
                return list.get(i);
            }
        }
        return null;
    }


    public boolean addGood(ArrayList<Good> addingGood) {
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals(addingGood.getFirst().getName())) {
                goods.addAll(addingGood);
                return true;
            }
        }

        for(ArrayList<Good> goods : list) {
            if(goods.isEmpty()) {
                goods.addAll(addingGood);
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        for (ArrayList<Good> goods : list) {
            if(goods.isEmpty())
                return false;
        }
        return true;
    }

}
