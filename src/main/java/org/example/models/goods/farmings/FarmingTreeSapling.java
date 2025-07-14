package org.example.models.goods.farmings;

import org.example.models.goods.GoodType;

import java.util.ArrayList;
import java.util.List;

public class FarmingTreeSapling extends Farming {
    private FarmingTreeSaplingType type;
    private ArrayList<Integer> state =  new ArrayList<>(
            List.of(0,0,0,0)
    );
    private boolean isTree = false;

    public FarmingTreeSapling(FarmingTreeSaplingType type) {
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
                state.set(i, state.get(i)+1);
                return;
            }
        }
        isTree = true;
    }

    public boolean isTree() {
        return isTree;
    }

    public FarmingTreeType getTreeType() {
        return type.getFarmingTreeType();
    }
}
