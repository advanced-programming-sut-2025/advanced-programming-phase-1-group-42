package org.example.models.game_structure;

import org.example.models.goods.Good;

import java.util.ArrayList;

public class Gift {
    private ArrayList<Good> list;

    public Gift(ArrayList<Good> list) {
        this.list = list;
    }

    public ArrayList<Good> getList() {
        return list;
    }

    public void setList(ArrayList<Good> list) {
        this.list = list;
    }
}
