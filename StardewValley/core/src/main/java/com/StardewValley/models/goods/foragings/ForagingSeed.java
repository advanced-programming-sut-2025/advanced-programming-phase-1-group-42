package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.farmings.FarmingCropType;

import java.util.ArrayList;
import java.util.List;

public class ForagingSeed extends Foraging {
    private final ForagingSeedType type;
    private final ArrayList<Integer> state = new ArrayList<>(List.of(0, 0, 0, 0, 0));
    private boolean isCrop = false;

    public ForagingSeed(ForagingSeedType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getSellPrice() {
        return type.getSellPrice();
    }

    @Override
    public GoodType getType() {
        return type;
    }

    public void dailyChange() {
        for (int i = 0; i < state.size(); i++) {
            if (!state.get(i).equals(type.getStages().get(i))) {
                state.set(i, state.get(i) + 1);
                return;
            }
        }
        isCrop = true;
    }

    public boolean isCrop() {
        return isCrop;
    }

    public FarmingCropType getCropType() {
            return type.getFarmingCropType();
    }

    @Override
    public String toString() {
        return "ForagingSeed:" +
                "\ntype: " + type +
                "\nstate: " + state +
                "\nisCrop: " + isCrop +
                "\nlevel: " + level + "\n";
    }

}
