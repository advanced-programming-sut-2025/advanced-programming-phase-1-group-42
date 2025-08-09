package com.StardewValley.models.goods.tools;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.interactions.Player;


public class Tool extends Good {
    private ToolType toolType;
    public int capacity;

    public Tool(ToolType toolType) {
        this.toolType = toolType;
        if(toolType == ToolType.WATERING_CAN)
            this.capacity = 40;
        else
            this.capacity = -1;
    }

    public int getEnergy(Player player) {
        int finalEnergy = toolType.getEnergy();
        finalEnergy -= toolType.getLevel().getLevelNumber();
        if((toolType == ToolType.HOE || toolType == ToolType.WATERING_CAN) &&
            player.getSkill().getFarmingLevel() == 4)
            finalEnergy--;
        if((toolType == ToolType.PICKAXE) && player.getSkill().getMiningLevel() == 4)
            finalEnergy--;
        if((toolType == ToolType.AXE) && player.getSkill().getForagingLevel() == 4)
            finalEnergy--;
        if((toolType == ToolType.TRAINING_FISHING_POLE || toolType == ToolType.BAMBOO_FISHING_POLE ||
            toolType == ToolType.FIBERGLASS_FISHING_POLE ||
            toolType == ToolType.IRIDIUM_FISHING_POLE) && player.getSkill().getFishingLevel() == 4)
            finalEnergy--;

        return Math.max(finalEnergy, 0);
    }

    @Override
    public String getName() {
        return toolType.getName();
    }

    @Override
    public int getSellPrice() {
        return toolType.getSellPrice();
    }

    @Override
    public GoodType getType() {
        return toolType;
    }

    public ToolLevel getToolLevel() {
        return toolType.getLevel();
    }
}
