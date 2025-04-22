package org.example.models.game_structure;

import org.example.models.goods.Good;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<ArrayList<Good>> list;

    public ArrayList<ArrayList<Good>> getList() {
        return list;
    }

    public void setList(ArrayList<ArrayList<Good>> list) {
        this.list = list;
    }

}
