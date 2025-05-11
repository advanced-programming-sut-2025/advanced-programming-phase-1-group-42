package org.example.models.goods.foragings;

import org.example.models.goods.GoodType;
import org.example.models.goods.farmings.FarmingCropType;
import org.example.models.goods.farmings.FarmingTreeType;

import java.util.ArrayList;
import java.util.List;

public class ForagingMixedSeed extends Foraging {
    private ForagingMixedSeedType type;
    private ForagingSeedType seedType;
    private ArrayList<Integer> state = new ArrayList<>(
            List.of(0,0,0,0));
    private boolean isCrop = false;

    public ForagingMixedSeed(ForagingMixedSeedType type) {
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

    public void setForagingSeedType(ForagingSeedType type) {
        seedType = type;
    }

    public void dailyChange() {
        for (int i = 0; i < state.size(); i++) {
            if (!state.get(i).equals(seedType.getStages().get(i))) {
                state.set(i, state.get(i)+1);
                return;
            }
        }
        isCrop = true;
    }

    public boolean isCrop() {
        return isCrop;
    }

    public FarmingCropType getCropType() {
        return seedType.getFarmingCropType();
    }

}
