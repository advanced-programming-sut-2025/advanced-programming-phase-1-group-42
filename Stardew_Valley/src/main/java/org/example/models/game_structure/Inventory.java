package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.goods.Good;

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


    public boolean addGood(Good good) {
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals(good.getName())) {
                goods.add(good);
                return true;
            }
        }

        for(ArrayList<Good> goods : list) {
            if(goods.isEmpty()) {
                goods = new ArrayList<>();
                goods.add(good);
                return true;
            }
        }
        return false;
    }

}
