package com.StardewValley.models.goods.farmings;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;

public class FarmingTree extends Farming {
    private FarmingTreeType type;
    private int harvestCycleCounter = 0;
    private boolean isFruitAvailable = false;

    public FarmingTree(FarmingTreeType type) {
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

    public void treeDayResult() {
        if (isFruitAvailable) {
            isFruitAvailable = false;
            harvestCycleCounter = 0;
        }
       for (Season season : type.getSeasons()) {
           if (season.equals(AppClient.getCurrentGame().getDateTime().getSeason())){
               harvestCycleCounter++;
               if (harvestCycleCounter == type.getFruitHarvestCycle()) {
                   isFruitAvailable = true;
                   break;
               }
           }
       }
    }

    public boolean isFruitAvailable() {
        return isFruitAvailable;
    }

    @Override
    public String toString() {
        return "FarmingTree: \n" +
                "type: " + type +
                "\nharvestCycleCounter: " + harvestCycleCounter +
                "\nisFruitAvailable: " + isFruitAvailable +
                "\nlevel: " + level + "\n";
    }
}
