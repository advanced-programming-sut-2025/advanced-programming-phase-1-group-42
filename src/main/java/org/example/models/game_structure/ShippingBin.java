package org.example.models.game_structure;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.interactions.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ShippingBin extends Good{
    private final HashMap<Player, ArrayList<ArrayList<Good>>> list;

    public ShippingBin() {
        list = new HashMap<>();
    }

    public void addGood(ArrayList<Good> addingGood, Player player) {
        if(!list.containsKey(player))
            list.put(player, new ArrayList<>());

        for (ArrayList<Good> goods : list.get(player)) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals(addingGood.getFirst().getName())) {
                goods.addAll(addingGood);
            }
        }

        for(ArrayList<Good> goods : list.get(player)) {
            if(goods.isEmpty()) {
                goods.addAll(addingGood);
            }
        }

        list.get(player).add(new ArrayList<>(addingGood));
    }

    @Override
    public String getName() {
        return "ShippingBin";
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

    @Override
    public GoodType getType() {
        return null;
    }
}
