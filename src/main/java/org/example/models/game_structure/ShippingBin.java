package org.example.models.game_structure;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.interactions.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ShippingBin extends Good {
    private final Coordinate coordinate;
    private final HashMap<Player, ArrayList<ArrayList<Good>>> list;

    public ShippingBin(Coordinate coordinate, HashMap<Player, ArrayList<ArrayList<Good>>> list) {
        this.coordinate = coordinate;
        this.list = list;
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

    public void emptyShippingBin() {
        for(Player player : list.keySet()) {
            int totalSellPrice = 0;
            for (ArrayList<ArrayList<Good>> goodsLists : list.values()) {
                for (ArrayList<Good> goods : goodsLists) {
                    totalSellPrice += (goods.getFirst().getSellPrice()) * goods.size();
                    goods.clear(); // Clears each individual Good list one by one
                }
            }
            player.getWallet().increaseBalance(totalSellPrice);
        }
    }

    public HashMap<Player, ArrayList<ArrayList<Good>>> getList() {
        return list;
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

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
