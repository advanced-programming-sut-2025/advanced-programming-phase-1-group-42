package org.example.models.interactions.PlayerBuildings;

import org.example.models.Pair;

public enum CoopType {
    COOP("Coop", 4,new Pair<>(6,3),4000,300,100),
    BIG_COOP("Big Coop", 8,new Pair<>(6,3),10000,400,150),
    DELUXE_COOP("Deluxe Coop", 12,new Pair<>(6,3),20000,500,200);
    private final String name;
    private final int capacity;
    private final Pair<Integer, Integer> size;
    private final int cost;
    private final int wood;
    private final int stone;


    CoopType(String name, int capacity , Pair<Integer, Integer> size, int cost, int wood, int stone) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.cost = cost;
        this.wood = wood;
        this.stone = stone;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
    public Pair<Integer, Integer> getSize() {
        return size;
    }
    public int getCost() {
        return cost;
    }
    public int getWood() {
        return wood;
    }
    public int getStone() {
        return stone;
    }

}
