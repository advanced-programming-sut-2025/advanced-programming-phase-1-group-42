package com.StardewValley.models.game_structure;

import com.StardewValley.models.goods.Good;

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
